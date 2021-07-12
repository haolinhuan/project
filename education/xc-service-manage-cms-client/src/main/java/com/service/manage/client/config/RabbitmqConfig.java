package com.service.manage.client.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    //队列的bean名称
    public static final String QUEUE_CMS_POSTPAGE = "queue_cms_postpage";

    //交换机的名称
    public static final String EX_ROUTING_CMS_POSTPAGE = "ex_routing_cms_postpage";

    //队列名称
    @Value("xuecheng.mq.queue")
    public String queuePostPageName;

    //routingKey 即站点Id
    @Value("xuecheng.mq.routingKey")
    public String routingKey;

    /**
     * 交换机配置使用的direct类型
     */
    @Bean(EX_ROUTING_CMS_POSTPAGE)
    public Exchange exchangeName() {
        return ExchangeBuilder.directExchange(EX_ROUTING_CMS_POSTPAGE).durable(true).build();
    }

    @Bean(QUEUE_CMS_POSTPAGE)
    public Queue queueName() {
        Queue queue = new Queue(queuePostPageName);
        return queue;
    }

    /**
     * 绑定到交换机
     */
    public Binding bindingName(@Qualifier(EX_ROUTING_CMS_POSTPAGE) Exchange exchange,
                               @Qualifier(QUEUE_CMS_POSTPAGE) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();

    }




}
