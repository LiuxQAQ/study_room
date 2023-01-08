package com.lx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.UserInfoDto;
import com.lx.domain.entity.User;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-10-30 17:12:19
 */
public interface UserService extends IService<User> {


    ResponseResult getUserInfo();

    ResponseResult updateUserInfo(UserInfoDto userInfoDto);

    ResponseResult getUserDetail();

}
