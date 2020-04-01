package com.kang;

import com.kang.dto.OrderMessageDto;
import com.kang.sender.RabbitSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootProducerApplicationTests {

    @Autowired
    private RabbitSender sender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSender1() throws Exception {

        Map<String,Object> map = new HashMap<>(5);

        map.put("number","123456");
        map.put("send_time", LocalDateTime.now());

        sender.send("test springboot sender",map);

        Thread.sleep(2000);

    }

    @Test
    public void testSender2() throws Exception {
        OrderMessageDto dto = new OrderMessageDto(1l,"测试订单",250.0,LocalDateTime.now());
        sender.sendOrder(dto);

        Thread.sleep(2000);

    }

}
