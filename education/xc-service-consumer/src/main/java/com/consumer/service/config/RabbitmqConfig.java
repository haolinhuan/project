package com.consumer.service.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_INFORM_EMAIL = "query_inform_email";//邮件队列
    public static final String QUEUE_INFORM_SMS = "query_inform_sms";//短信队列
    public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";//交换机名称

    public static final String ROUTING_EMAIL = "inform.#.email.#";
    public static final String ROUTING_SMS = "inform.#.sms.#";

    /**
     * 交换机
     * ExchangeBuilder提供了fanout,direct,topic,header交换机类型的配置
     *
     * @return
     */
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange exchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();

    }

    @Bean(QUEUE_INFORM_SMS)
    public Queue queueSms(){
        Queue queue = new Queue(QUEUE_INFORM_SMS);
        return queue;
    }

    @Bean(QUEUE_INFORM_EMAIL)
    public Queue queueEmail(){
        Queue queue = new Queue(QUEUE_INFORM_EMAIL);
        return queue;
    }
    /**
     * 队列绑定交换机
     */
    @Bean
    public Binding bindEmail(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
                             @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_EMAIL).noargs();
    }

    /**
     * 队列绑定交换机
     */
    @Bean
    public Binding bindSms(@Qualifier(QUEUE_INFORM_SMS) Queue queue,
                             @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_SMS).noargs();
    }



}
