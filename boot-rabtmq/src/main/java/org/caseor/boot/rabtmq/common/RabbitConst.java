package org.caseor.boot.rabtmq.common;

/**
 * rabbitmq常量
 * @author Fu Kai
 * @since 20201021
 */

public interface RabbitConst {
    /**
     * 直接队列
     */
    String DIRECT_QUEUE = "direct.queue";

    /**
     * 队列2
     */
    String TWO_QUEUE = "2.queue";

    /**
     * 队列3
     */
    String THREE_QUEUE = "3.queue";

    /**
     * 延迟队列
     */
    String DELAY_QUEUE = "delay.queue";

    /**
     * 分发模式 Exchange
     */
    String DIRECT_EXCHANGE = "direct.exchange";

    /**
     * 分发模式 Exchange
     */
    String FANOUT_EXCHANGE = "fanout.exchange";

    /**
     * 主题模式 Exchange
     */
    String TOPIC_EXCHANGE = "topic.exchange";

    /**
     * 延迟队列交换器
     */
    String DELAY_EXCHANGE = "delay.exchange";

    /**
     * 路由1, #代表匹配任意多个, 可匹配queue.a queue.a.b
     */
    String TOPIC_ROUTING_KEY_ONE = "queue.#";

    /**
     * 路由2, *代表任意一个, 可匹配a.queue, 不可匹配a.b.queue
     */
    String TOPIC_ROUTING_KEY_TWO = "*.queue";

    /**
     * 路由3, 固定路由3.queue
     */
    String TOPIC_ROUTING_KEY_THREE = "3.queue";

}
