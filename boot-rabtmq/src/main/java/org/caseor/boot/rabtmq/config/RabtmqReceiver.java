package org.caseor.boot.rabtmq.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Fu Kai
 * @since 20210524
 */
@Component
public class RabtmqReceiver {

  @RabbitListener(queues = RabtmqConfig.FANOUT_QUEUE1)
  public void recvLogAll(String msg) {
    System.out.println("log.all:" + msg);
  }
}
