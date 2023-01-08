package com.lx.domain.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoomDetailVo {
    private Long id;

    //名字
    private String name;
    private Double longitude;

    private Double latitude;
    //所在城市
    private String city;
    //所在区
    private String district;
    //详细地址
    private String address;

    private int seat;

    //座位图
    private String seatPhoto;
    //自习室图片
    private String photo;
    //自习室描述
    private String roomDescribe;
    //价格
    private Double cost;
    private String openingHours;
}
