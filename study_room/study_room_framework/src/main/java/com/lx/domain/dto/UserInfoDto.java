package com.lx.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoDto {
    private Long id;
    //电话
    private Long phone;
    //邮箱
    private String email;
    //头像
    private String portrait;
    //昵称
    private String nickName;
    //性别
    private String gender;
}
