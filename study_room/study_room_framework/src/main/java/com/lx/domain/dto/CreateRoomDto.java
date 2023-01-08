package com.lx.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateRoomDto {
    //名字
    private String name;
    //详细地址
    private String address;
    //所在城市
    private String city;
    //归属人  商人id
    private Long mId;
    private Long phone;

    //价格
    private Double cost;
    //经度
    private Double longitude;
    //纬度
    private Double latitude;
    //所在区
    private String district;
    //座位数量
    private Integer seat;
    //许可证图片
    private String licence;
    //座位图
    private String seatPhoto;
    //自习室图片
    private String photo;
    //自习室描述
    private String roomDescribe;
    //营业时间
    private String openingHours;
}
