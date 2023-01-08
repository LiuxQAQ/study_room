package com.lx.controller;

import com.lx.domain.ResponseResult;
import com.lx.domain.dto.UserInfoDto;
import com.lx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult getUserDetail(){
        return userService.getUserDetail();
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody UserInfoDto userInfoDto){
        return userService.updateUserInfo(userInfoDto);
    }

}
