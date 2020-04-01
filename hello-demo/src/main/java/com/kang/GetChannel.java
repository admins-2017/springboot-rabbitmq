package com.kang;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/30 15:31
 */
public class GetChannel {

//    private static Connection connection;
//
//    private static Channel channel;
//
//    public static Channel init() throws Exception{
//
//        //创建一个连接工厂
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        //配置连接信息
//        connectionFactory.setHost("127.0.0.1");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
//        //通过连接工厂创建连接
//        connection = connectionFactory.newConnection();
//        //通过连接创建一个Channel管道
//        channel = connection.createChannel();
//
//        return channel;
//    }
//
//    public static void close() throws IOException, TimeoutException {
//
//        channel.close();
//        connection.close();
//
//    }


}
