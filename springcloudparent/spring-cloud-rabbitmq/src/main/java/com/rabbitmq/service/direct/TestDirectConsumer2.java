package com.rabbitmq.service.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestDirectConsumer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建mq连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置连接rabbitMq的主机
        factory.setHost("192.168.31.50");
        factory.setPort(5672);
        factory.setVirtualHost("/ms");
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection = factory.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //参数1：交换机的名称
        //参数2：交换机的类型
        channel.exchangeDeclare("fanout1","fanout");

        //创建临时队列
        String queueName = channel.queueDeclare().getQueue();

        //发送消息
        channel.queueBind(queueName,"fanout1","");

        channel.basicConsume(queueName,false, new DefaultConsumer(channel){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("message:"+new String(body));
                    }
                }
        );
    }
}
