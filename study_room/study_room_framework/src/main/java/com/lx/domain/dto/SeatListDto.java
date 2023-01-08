package com.lx.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SeatListDto {
    //自习室id
    private Long rid;
    // 预约时间
    private String dateTime;

    private Date startTime;

    private Date endTime;

}
