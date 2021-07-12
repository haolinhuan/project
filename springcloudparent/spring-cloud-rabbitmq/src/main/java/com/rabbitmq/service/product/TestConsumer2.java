package com.rabbitmq.service.product;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestConsumer1 {

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

        //声明一个队列
        //1.队列名称，如果队列不存在，就自动创建
        //2.用来定义队列特性是否要持久化 true持久化队列 false 不持久化
        //3.exclusive 是否独占队列，如果是，别的通道不能绑定该队列
        //4.autoDelete 是否在消费完成后自动删除队列
        //5.额外附加参数
        //跟生产者保持一致
        channel.queueDeclare("queue1",false,false,false,null);

        //发布消息
        //1.交换机名称
        //2.是否开启自动确认
        //3.回调函数
        channel.basicConsume("queue1",true, new DefaultConsumer(channel){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                        System.out.println("message:"+new String(body));
                    }
                }
        );
    }
}
