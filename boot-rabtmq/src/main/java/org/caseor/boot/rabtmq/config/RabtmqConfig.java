package org.caseor.boot.rabtmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * @author Fu Kai
 * @since 20210524
 */

public class RabtmqConfig {
  public static final String FANOUT_EXCHANGE = "fanout.exchange";

  @Bean(name = FANOUT_EXCHANGE)
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(FANOUT_EXCHANGE, true, false);
  }

  public static final String FANOUT_QUEUE1 = "fanout.queue1";

  @Bean(name = FANOUT_QUEUE1)
  public Queue fanoutQueue1() {
    return new Queue(FANOUT_QUEUE1, true, false, false);
  }

  @Bean
  public Binding bindingSimpleQueue1(@Qualifier(FANOUT_QUEUE1) Queue fanoutQueue1, @Qualifier(FANOUT_EXCHANGE) FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
  }

}
