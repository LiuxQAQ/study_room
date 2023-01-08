package com.lx.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.UserInfoDto;
import com.lx.domain.entity.User;
import com.lx.domain.vo.UserDetailVo;
import com.lx.mapper.UserMapper;
import com.lx.service.UserService;
import com.lx.utils.BeanCopyUtils;
import com.lx.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-10-30 17:12:33
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public ResponseResult getUserInfo() {
        Long id = SecurityUtils.getUserId();
        User user = getById(id);
        UserDetailVo userDetailVo = BeanCopyUtils.copyBean(user, UserDetailVo.class);
        return ResponseResult.okResult(userDetailVo);
    }

    @Override
    public ResponseResult updateUserInfo(UserInfoDto userInfoDto) {
        User user = BeanCopyUtils.copyBean(userInfoDto, User.class);
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId,user.getId())
                .set(User::getEmail,user.getEmail())
                .set(User::getPhone,user.getPhone())
                .set(User::getProvince,user.getProvince())
                .set(User::getGender,user.getGender())
                .set(User::getNickName,user.getNickName());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserDetail() {
        return null;
    }
}
