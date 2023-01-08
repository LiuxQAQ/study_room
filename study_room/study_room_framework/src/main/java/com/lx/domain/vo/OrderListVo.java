package com.lx.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class OrderListVo {
    private Long id;

    private Long uid;
    private String nickName;
    //自习室id
    private Long rid;
    //座位
    private Integer seat;


    private Date startTime;

    private Date endTime;

    private Integer totalPrice;

    private Integer minutes;
}
