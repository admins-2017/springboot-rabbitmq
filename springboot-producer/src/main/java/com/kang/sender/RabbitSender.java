package com.kang.sender;

import com.kang.dto.OrderMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/30 16:31
 */
@Component
@Slf4j
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ConfirmCallback confirmCallback = (correlationData, ack, s) -> {
            log.info("correlationData={}",correlationData);
            log.info("ack={}",ack);
            if (ack){
                log.info("更新订单状态");
            }else {
                log.error("处理异常:{}",s);
            }
    };

    private final RabbitTemplate.ReturnCallback callback = (message, errorCode, text, exchange, routingKey)
            -> {
        log.error("return exchange :{},routingKey:{},message:{},code:{},text:{}"
            ,exchange,routingKey,message,errorCode,text);
        log.info("找不到对应的队列");
    };


    public void send(Object message , Map<String,Object> properties) throws Exception{

        MessageHeaders messageHeaders = new MessageHeaders(properties);

        Message msg = MessageBuilder.createMessage(message,messageHeaders);

        rabbitTemplate.setConfirmCallback(confirmCallback);

        rabbitTemplate.setReturnCallback(callback);

        CorrelationData correlationData = new CorrelationData("test-hello"+ LocalDateTime.now().toString());

        rabbitTemplate.convertAndSend("exchange-001","springboot.hello",msg,correlationData);
    }


    public void send2(Object message , Map<String,Object> properties) throws Exception{

        MessageHeaders messageHeaders = new MessageHeaders(properties);

        Message msg = MessageBuilder.createMessage(message,messageHeaders);

        rabbitTemplate.setConfirmCallback(confirmCallback);

        rabbitTemplate.setReturnCallback(callback);

        CorrelationData correlationData = new CorrelationData("test-hello"+ LocalDateTime.now().toString());

        rabbitTemplate.convertAndSend("exchange-002","springboot2.hello",msg,correlationData);
    }

    public void sendOrder(OrderMessageDto message ) throws Exception{

        rabbitTemplate.setConfirmCallback(confirmCallback);

        rabbitTemplate.setReturnCallback(callback);

        CorrelationData correlationData = new CorrelationData("test-hello"+ LocalDateTime.now().toString());

        rabbitTemplate.convertAndSend("order-exchange","order.test",message,correlationData);
    }

}
