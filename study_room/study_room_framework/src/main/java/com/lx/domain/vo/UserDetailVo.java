package com.lx.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetailVo {

    private Long id;
    //电话
    private Long phone;
    //邮箱
    private String email;
    //用户名
    private String userName;
    //密码
    private String password;
    //微信openId
    private String openId;
    //联合id
    private String wxUnionId;
    //头像
    private String portrait;
    //昵称
    private String nickName;
    //性别
    private String gender;

    private String city;
    //省
    private String province;
    //国
    private String country;
}
