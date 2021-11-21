package org.caseor.boot.rabtmq.handler;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;


import org.caseor.boot.rabtmq.common.RabbitConst;
import org.caseor.boot.rabtmq.message.MessageStruct;
import org.caseor.common.core.util.JsonUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 队列3处理器
 * @author Fu Kai
 * @since 20201021
 */

@Component
@Slf4j
@RabbitListener(queues = RabbitConst.THREE_QUEUE)
public class ThreeQueueHandler {
  /**
   * 手动处理消息
   * @param messageStruct
   * @param message
   * @param channel
   */
  @RabbitHandler
  public void directHandleManualAck(MessageStruct messageStruct, Message message, Channel channel) {
    final long deliveryTag = message.getMessageProperties().getDeliveryTag();
    try {
      log.info(" 队列3处理器 手动ACK, 接受消息:{} ", JsonUtil.serialize(messageStruct));
      channel.basicAck(deliveryTag, false);
    } catch (IOException e) {
      try {
        // 处理失败, 重新压入MQ
        channel.basicRecover();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    }
  }
}
