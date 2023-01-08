package com.lx.domain.dto;

import lombok.Data;

@Data
public class UpdateRoomDto {
    private Long id;
    //名字
    private String name;

    private Double longitude;

    private Double latitude;
    //所在城市
    private String city;
    //所在区
    private String district;
    //状态
    private Integer state;
    //自习室图片
    private String photo;
    //自习室描述
    private String roomDescribe;
    //价格
    private Double cost;
}
