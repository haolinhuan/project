package com.rabbitmq.service.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestProvidor {
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
        channel.exchangeDeclare("direct1","direct");

        String routingKey= "info";
        //发送消息
        channel.basicPublish("fanout1",routingKey,null,"direct_providor:".getBytes());
        channel.close();
        connection.close();
    }
}
