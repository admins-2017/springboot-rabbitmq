package com.kang.receiver;

import com.kang.dto.OrderMessageDto;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;

import java.util.Map;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/31 10:22
 */
@Component
@Slf4j
public class RabbitReceiver {

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                value = @Queue(value = "queue-001",durable = "true"),
                exchange = @Exchange(value = "exchange-001" , type = "topic" ,durable = "true"),
                key = "springboot.*"
            )
    )
    public void onMessage(Message message , Channel channel) throws Exception{
        log.info("-----------------------------------");
        log.info("message : {}",message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        //手工ack
        channel.basicAck(deliveryTag,false);
    }


    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "queue-002",
                                   durable = "true"),
                    exchange = @Exchange(value = "exchange-002" , type = "topic" ,durable = "true"),
                    key = "springboot2.*"
            )
    )
    public void onMessage2(Message message , Channel channel) throws Exception{
        log.info("-----------------------------------");
        log.info("message : {}",message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        //手工ack
        channel.basicAck(deliveryTag,false);
    }


    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            value = "${spring.rabbitmq.listener.order.queue.name}",
                            durable = "${spring.rabbitmq.listener.order.queue.durable}"),
                    exchange = @Exchange(
                            value = "${spring.rabbitmq.listener.order.exchange.name}" ,
                            type = "${spring.rabbitmq.listener.order.exchange.type}" ,
                            durable = "${spring.rabbitmq.listener.order.exchange.durable}" ,
                            ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
                    key = "${spring.rabbitmq.listener.order.key}"
            )
    )
    public void onOrderMessage(@Payload OrderMessageDto messageDto, @Headers Map<String,Object> headers, Channel channel) throws Exception{
        log.info("message : {}",messageDto.toString());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        //手工ack
        channel.basicAck(deliveryTag,false);
    }
}
