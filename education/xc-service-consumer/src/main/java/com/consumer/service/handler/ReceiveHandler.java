package com.consumer.service.handler;

import com.consumer.service.config.RabbitmqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandler {

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
    public void getEmail(String msg, Message message, Channel channel){
        System.out.println(msg);
        System.out.println(message);
        System.out.println(channel);
    }

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
    public void getSms(String msg, Message message, Channel channel){
        System.out.println(msg);
        System.out.println(message);
        System.out.println(channel);
    }
}
