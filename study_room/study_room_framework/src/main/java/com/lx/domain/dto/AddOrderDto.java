package com.lx.domain.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class AddOrderDto {
    //用户id
    private Long uid;
    //自习室id
    private Long rid;
    //座位
    private Integer seat;
    // 开始时间
    private Date startTime;
    // 结束时间
    private Date endTime;
    // 总价
    private Integer totalPrice;
}
