package com.product.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Producer {
    private static final String QUEUE = "helloword";

    //队列名称
    public static void main(String[] args) throws IOException, TimeoutException {
        //队列名称
        Connection connection = null;
        Channel channel = null;

        try {
            ConnectionFactory rabbitFactory = new ConnectionFactory();

            rabbitFactory.setHost("192.168.31.50");
            rabbitFactory.setPort(5672);
            rabbitFactory.setUsername("root");
            rabbitFactory.setPassword("123456");
            rabbitFactory.setVirtualHost("/");

            //创建与RabbitMQ服务的TCP连接
            connection = rabbitFactory.newConnection();

            //创建与Exchange的通道，每个连接可以创建多个通道，每个通道代表一个会话任务
            channel = connection.createChannel();

            /**
             * 声明队列，如果Rabbit中没有此队列将自动创建
             * param1:队列名称
             * param2:是否持久化
             * param3:队列是否独占此连接
             * param4:队列不再使用时是否自动删除此队列
             * param5:队列参数
             */
            channel.queueDeclare(QUEUE, true, false, false, null);

            String message = "郝林欢" + System.currentTimeMillis();

            /**
             * 消息发布方法
             * param1：Exchange的名称，如果没有指定，则使用Default Exchange
             * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列
             * param3:消息包含的属性
             * param4：消息体
             */

            channel.basicPublish("", QUEUE, null, message.getBytes());

            System.out.println("Send Message is:'" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(channel !=null){
                channel.close();
            }
            if(connection !=null){
                connection.close();
            }
        }


    }
}
