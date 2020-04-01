package com.kang;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/30 15:17
 */
public class Consumer extends DefaultConsumer{

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public Consumer(Channel channel) {
        super(channel);
    }


    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.err.println("-----------consume message----------");
        System.err.println("consumerTag: " + consumerTag);
        System.err.println("envelope: " + envelope);
        System.err.println("properties: " + properties);
        System.err.println("body: " + new String(body));
    }

    public static void main(String [] args) throws Exception {
        //创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //配置连接信息
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        //通过连接创建一个Channel管道
        Channel channel = connection.createChannel();

        String queueName="hello-queue";

        //声名队列
        channel.queueDeclare(queueName,true,false,false,null);
        int i =0;
        while (true) {
            channel.basicConsume(queueName, true, new Consumer(channel));

        }

//        channel.close();
//        connection.close();
    }
}
