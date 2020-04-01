package com.kang;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/30 15:17
 */
public class Procuder {

    public static void main (String [] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //配置连接信息
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        //通过连接创建一个Channel管道
        Channel channel = connection.createChannel();

        for (int i = 0; i < 5; i++) {
            //通过channel 发送数据
            channel.basicPublish("hello-exchange",
                    "hello.key",null,"hello rabbitMQ".getBytes());
        }

        //关闭连接
        channel.close();
        connection.close();
    }
}
