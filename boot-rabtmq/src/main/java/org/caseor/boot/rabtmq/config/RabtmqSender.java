package org.caseor.boot.rabtmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Fu Kai
 * @since 20210524
 */
@Component
public class RabtmqSender {

  private final RabbitTemplate rabbitTemplate;

  @Autowired
  public RabtmqSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  /**
   * 发送消息
   */
  public void send(String exchange, String routingKey, Message message) {
    CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
    System.out.println("开始发送消息 : " + message);
    rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationId);
    System.out.println("结束发送消息 : " + message);
  }
}
