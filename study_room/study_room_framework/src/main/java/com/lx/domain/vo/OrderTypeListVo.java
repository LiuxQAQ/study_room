package com.lx.domain.vo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class OrderTypeListVo {
    private Long id;
    //用户id
    private Long uid;
    private Long rid;
    //自习室id
    private String name;
    //座位
    private Integer seat;
    //预约时间
    private Date datetime;
    private Date startTime;
    private Date endTime;
    //状态
    private Integer state;
    private String city;
    private Integer totalPrice;
    private String code;
    private boolean showCode;

}
