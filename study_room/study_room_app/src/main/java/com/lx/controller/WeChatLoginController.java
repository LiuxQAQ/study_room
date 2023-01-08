package com.lx.controller;


import com.lx.domain.ResponseResult;
import com.lx.domain.dto.WXAuthDto;
import com.lx.domain.entity.WXAuth;
import com.lx.service.WeChatLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class WeChatLoginController {

    @Autowired
    private WeChatLoginService weChatLoginService;

    // getSessionID
    @GetMapping("/getSessionID")
    public ResponseResult getSessionID(String code){
        return weChatLoginService.getSessionID(code);
    }

    @PostMapping("/authLogin")
    public ResponseResult authLogin(@RequestBody WXAuthDto wxAuthDto ){
        return weChatLoginService.authLogin(wxAuthDto);
    }
}
