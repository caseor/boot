package org.caseor.boot.kafka.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author Fu Kai
 * @since 20210722
 * 1. 若发送消息时指定了分区(即自定义分区策略), 则直接将消息append到指定分区
 * 2. 若发送消息时未指定 partition, 但指定了 key(kafka允许为每条消息设置一个key), 则对key值进行hash计算, 根据计算结果路由到指定分区, 这种情况下可以保证同一个 Key 的所有消息都进入到相同的分区
 * 3. partition 和 key 都未指定, 则使用kafka默认的分区策略, 轮询选出一个 partition
 */

public class CustomPartitioner implements Partitioner {
  @Override
  public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
    return 0;
  }

  @Override
  public void close() {

  }

  @Override
  public void configure(Map<String, ?> configs) {

  }
}
