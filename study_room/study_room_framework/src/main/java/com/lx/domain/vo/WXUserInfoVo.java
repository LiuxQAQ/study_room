package com.lx.domain.vo;

import lombok.Data;

@Data
public class WXUserInfoVo {
    private String openId;
    private String nickName;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
}
