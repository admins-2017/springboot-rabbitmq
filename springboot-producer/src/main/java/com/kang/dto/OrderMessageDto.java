package com.kang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kang
 * @version 1.0
 * @date 2020/4/1 9:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessageDto implements Serializable {

    private Long orderNumber;

    private String orderName;

    private Double price;

    private LocalDateTime time;

}
