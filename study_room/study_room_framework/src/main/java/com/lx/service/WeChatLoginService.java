package com.lx.service;

import com.lx.domain.ResponseResult;
import com.lx.domain.dto.WXAuthDto;

public interface WeChatLoginService {
    ResponseResult getSessionID(String code);

    ResponseResult authLogin(WXAuthDto wxAuthDto);
}
