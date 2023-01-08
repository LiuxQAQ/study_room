package com.lx.domain.vo;

import lombok.Data;

@Data
public class RoomVo {
    //自习室id@TableId
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
    //座位图
    private String seatPhoto;
    //自习室图片
    private String photo;
    //营业时间
    private String openingHours;

}
