package org.caseor.boot.kafka.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.stereotype.Component;

/**
 * @author Fu Kai
 * @since 20210722
 */

@Component
public class MessageConsumer {

  @Autowired
  ConsumerFactory consumerFactory;

  @KafkaListener(topics = {"topic1"})
  public void onMessage1(ConsumerRecord<?, ?> record) {
    // 消费的哪个topic、partition的消息,打印出消息内容
    System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
  }


  /**
   * 异常处理器
   */
  @Bean
  public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
    return (msg, exception, consumer) -> {
      System.out.println("消费异常: " + msg.getPayload());
      return null;
    };
  }


  /**
   * 指定topic、partition、offset消费
   * 时监听topic1和topic2，监听topic1的0号分区、topic2的 "0号和1号" 分区，指向1号分区的offset初始值为8
   **/
  @KafkaListener(id = "consumer1", groupId = "felix-group", topicPartitions = {
    @TopicPartition(topic = "topic1", partitions = {"0"}),
    @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
  }, errorHandler = "consumerAwareErrorHandler")
  public void onMessage2(ConsumerRecord<?, ?> record) {
    System.out.println("topic:" + record.topic() + "|partition:" + record.partition() + "|offset:" + record.offset() + "|value:" + record.value());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
    ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
    factory.setConsumerFactory(consumerFactory);
    // 被过滤的消息将被丢弃
    factory.setAckDiscarded(true);
    // 消息过滤策略
    factory.setRecordFilterStrategy(consumerRecord -> {
      if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
        return false;
      }
      // 返回true消息则被过滤
      return true;
    });
    return factory;
  }

}
