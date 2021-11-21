package org.caseor.boot.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fu Kai
 * @since 20210722
 */

@RestController
public class MessageProducer {

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;


  private final String TOPIC_1 = "topic1";

  @GetMapping("/produce/{msg}")
  public int produceMessage(@PathVariable("msg") String msg) {
    kafkaTemplate.send(TOPIC_1, msg);
    return 1;
  }

  /**
   * 带事务, 抛出异常, 不会生产消息
   */
  @GetMapping("/produceWithTransaction/{msg}")
  public int produceWithTransaction(@PathVariable("msg") String msg) {
    kafkaTemplate.executeInTransaction(callback -> {
      callback.send("topic1", "test executeInTransaction" + msg);
      throw new RuntimeException("fail");
    });
    return 1;
  }

  /**
   * 不带事务, 即使抛出异常, 也会正常生产消息
   */
  @GetMapping("/produceWithoutTransaction/{msg}")
  public int produceWithoutTransaction(@PathVariable("msg") String msg) {
    // 不声明事务：后面报错但前面消息已经发送成功了
    kafkaTemplate.send("topic1", "test executeInTransaction" + msg);
    throw new RuntimeException("fail");
  }


}
