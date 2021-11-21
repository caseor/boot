package org.caseor.boot.rabtmq.config;


import lombok.extern.slf4j.Slf4j;

import org.caseor.boot.rabtmq.common.RabbitConst;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * rabbitmq配置类
 * @author Fu Kai
 * @since 20201021
 */

@Configuration
@Slf4j
public class RabbitConfig {
  public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
    connectionFactory.setPublisherReturns(true);
    connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMandatory(true);
    rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> log.info(" 消息发送成功:correlationData({}), ack({}), cause({}) ", correlationData, ack, cause)));
    rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> log.info(" 消息丢失:exchange({}) route({}) replyCode({}) replayText({}) message:{} ", exchange, routingKey, replyCode, replyText, message)));
    return rabbitTemplate;
  }

  // *************** Queue ***************

  /**
   * 生成 直接队列
   */
  @Bean
  public Queue directQueue() {
    return new Queue(RabbitConst.DIRECT_QUEUE);
  }

  /**
   * 生成 队列2
   */
  @Bean
  public Queue twoQueue() {
    return new Queue(RabbitConst.TWO_QUEUE);
  }

  /**
   * 生成 队列3
   */
  @Bean
  public Queue threeQueue() {
    return new Queue(RabbitConst.THREE_QUEUE);
  }

  /**
   * 生成 延迟队列
   */
  @Bean
  public Queue delayQueue() {
    return new Queue(RabbitConst.DELAY_QUEUE, true);
  }

  // *************** Exchange ***************

  /**
   * 分列模式 Exchange
   */
  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(RabbitConst.FANOUT_EXCHANGE);
  }

  /**
   * 主题模式 Exchange
   */
  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(RabbitConst.TOPIC_EXCHANGE);
  }

  /**
   * 自定义 Exchange
   */
  @Bean
  public CustomExchange delayExchange() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-delayed-type", "direct");
    return new CustomExchange(RabbitConst.DELAY_EXCHANGE, "x-delayed-message", true, false, args);
  }

  // *************** Binding ***************


  /**
   * 将 直接队列 绑定至 fanoutExchange
   */
  @Bean
  public Binding fanoutBinding1(Queue directQueue, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(directQueue).to(fanoutExchange);
  }

  /**
   * 将 队列2 绑定至 fanoutExchange
   */
  @Bean
  public Binding fanoutBinding2(Queue twoQueue, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(twoQueue).to(fanoutExchange);
  }

  /**
   * 将 队列3 绑定至 fanoutExchange
   */
  @Bean
  public Binding fanoutBinding3(Queue threeQueue, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(threeQueue).to(fanoutExchange);
  }

  /**
   * 主题模式 将 fanoutExchange 绑定至 topicExchange routingKey=queue.#
   */
  @Bean
  public Binding topicBinding1(FanoutExchange fanoutExchange, TopicExchange topicExchange) {
    return BindingBuilder.bind(fanoutExchange).to(topicExchange).with(RabbitConst.TOPIC_ROUTING_KEY_ONE);
  }

  /**
   * 主题模式 将 队列2 绑定至 topicExchange routingKey=*.queue
   */
  @Bean
  public Binding topicBinding2(Queue twoQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(twoQueue).to(topicExchange).with(RabbitConst.TOPIC_ROUTING_KEY_TWO);
  }

  /**
   * 主题模式 将 队列3 绑定至 topicExchange 固定routingKey=3.queue
   */
  @Bean
  public Binding topicBinding3(Queue threeQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(threeQueue).to(topicExchange).with(RabbitConst.TOPIC_ROUTING_KEY_THREE);
  }

  /**
   * 主题模式 将 延迟队列 绑定至 自定义Exchange 固定routingKey=delay.queue
   * @param delayQueue
   * @param customExchange
   * @return
   */
  @Bean
  public Binding delayBinding(Queue delayQueue, CustomExchange customExchange) {
    return BindingBuilder.bind(delayQueue).to(customExchange).with(RabbitConst.DELAY_QUEUE).noargs();
  }

}
