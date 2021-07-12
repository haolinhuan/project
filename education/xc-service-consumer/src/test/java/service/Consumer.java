package service;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    private static final String QUEUE = "helloword";

    public static void main(String[] args) {
        ConnectionFactory rabbitFactory = new ConnectionFactory();
        rabbitFactory.setHost("192.168.31.50");
        rabbitFactory.setPort(5672);
        rabbitFactory.setUsername("root");
        rabbitFactory.setPassword("123456");
        Connection connection =null;
        Channel channel = null;
        try {
            connection = rabbitFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE, true, false, false, null);
            DefaultConsumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,AMQP.BasicProperties properties,byte[] body) throws IOException {
                    String exchange = envelope.getExchange();
                    String routingKey = envelope.getRoutingKey();
                    long deliveryTag = envelope.getDeliveryTag();

                    String message = new String(body,"utf-8");
                    System.out.println("receive message.."+message);
                }
            };
            /**
             * 监听队列String queue, boolean autoAck,Consumer callback
             * 参数明细
             * 1、队列名称
             * 2、是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置
             为false则需要手动回复
             * 3、消费消息的方法，消费者接收到消息后调用此方法
             */
            channel.basicConsume(QUEUE, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {

            }
        }

    }
}
