package com.lx.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ManagementListVo {

    private Long id;

    //商家id
    private Long mid;

    private String merchantName;
    //自习室id
    private Long rid;

    private String roomName;
    //状态
    private Integer state;


}
