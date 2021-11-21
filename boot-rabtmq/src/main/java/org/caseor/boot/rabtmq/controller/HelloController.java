package org.caseor.boot.rabtmq.controller;

import org.caseor.boot.rabtmq.config.RabtmqConfig;
import org.caseor.boot.rabtmq.config.RabtmqSender;
import org.caseor.common.core.entity.R;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fu Kai
 * @since 20210524
 */

@RestController
public class HelloController {

  private final RabtmqSender rabtmqSender;

  @Autowired
  public HelloController(RabtmqSender rabtmqSender) {
    this.rabtmqSender = rabtmqSender;
  }

  @GetMapping("/hello")
  public R<?> hello() {
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setDelay(100000);
    String messageStr = "第一条消息延迟: 10s  hello !";
    Message message = new Message(messageStr.getBytes(), messageProperties);
    rabtmqSender.send(RabtmqConfig.FANOUT_EXCHANGE, RabtmqConfig.FANOUT_QUEUE1, message);
    return R.success(null, "");
  }

}
