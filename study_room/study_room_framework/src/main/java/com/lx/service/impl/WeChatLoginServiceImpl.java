package com.lx.service.impl;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lx.constants.SystemConstants;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.UserDto;
import com.lx.domain.dto.WXAuthDto;
import com.lx.domain.entity.LoginUser;
import com.lx.domain.entity.User;
import com.lx.domain.entity.WXUserInfo;
import com.lx.domain.vo.UserVo;
import com.lx.domain.vo.WXUserInfoVo;
import com.lx.enums.AppHttpCodeEnum;
import com.lx.mapper.UserMapper;
import com.lx.service.UserService;
import com.lx.service.WeChatLoginService;
import com.lx.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class WeChatLoginServiceImpl implements WeChatLoginService {

    @Value("weChat.appId")
    private String appId = "wx67173c9306315c33";
    @Value("weChat.appSecret")
    private String appSect = "ef0823e6c211affb56d0709a5e6da802";

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private WXServiceImpl wxService;
    @Resource
    private UserMapper userMapper;


    @Override
    public ResponseResult getSessionID(String code) {
        // 1. 拼接一个url，微信凭证校验接口
        // 2. 发起一个http的调用，获取微信的返回结果
        // 3. 存到redis中
        // 4. 生成一个sessionID，返回给前端，作为当前需要登陆用户的标识
        // 5. sessionID： 用户在点击微信登录的时候，我们可以标识是谁点击微信登录


        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx67173c9306315c33" + "&secret=ef0823e6c211affb56d0709a5e6da802"+"&js_code="+ code +"&grant_type=authorization_code";

        String res = HttpUtil.get(url);
        String uuid = UUID.randomUUID().toString();
        redisCache.setCacheObject(SystemConstants.WX_SESSION_ID+uuid,res,300, TimeUnit.MINUTES);
        HashMap<String , String> map = new HashMap<>();
        map.put("sessionId",uuid);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult authLogin(WXAuthDto wxAuthDto) {
        // 1. 通过WXAuth中的值进行解密
        // 2. 解密完成之后，会获取到微信的用户信息， 其中包含openId，性别，昵称，头像等信息
        // 3. openId是唯一的， 需要去user表中查询openId是否存在， 存在 就进行登录，不存在就进行注册
        // 4. 使用jwt生成一个token，给前端
        try {
//            String json = redisCache.getCacheObject(SystemConstants.WX_SESSION_ID + wxAuthDto.getSessionId());
//            JSONObject jsonObject = JSON.parseObject(json);
//            String sessionKey = String.valueOf(jsonObject.get("session_key"));

            String decrypt = wxService.wxDecrypt(wxAuthDto.getEncryptedData(), wxAuthDto.getSessionId(), wxAuthDto.getIv());
//            JSONObject decrypt = WXUtils.getUserInfo(wxAuthDto.getEncryptedData(), sessionKey, wxAuthDto.getIv());
            String json = redisCache.getCacheObject(SystemConstants.WX_SESSION_ID + wxAuthDto.getSessionId());
            JSONObject jsonObject = JSON.parseObject(json);

//            String decrypt = AESUtil.decrypt(wxAuthDto.getEncryptedData(), sessionKey);

            String openid = String.valueOf(jsonObject.get("openid"));
            WXUserInfo wxUserInfo = JSON.parseObject(decrypt, WXUserInfo.class);
//            WXUserInfo wxUserInfo = BeanCopyUtils.copyBean(decrypt, WXUserInfo.class);
            wxUserInfo.setOpenId(openid);
            String openId = wxUserInfo.getOpenId();
            User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenId, openId).last("limit 1"));
            UserDto userInfo = BeanCopyUtils.copyBean(wxUserInfo, UserDto.class);
            if (user==null){
                // 注册
                return this.register(userInfo);
            }else {
                // 登录
                userInfo.setId(user.getId());
                return this.login(userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }

    private ResponseResult login(UserDto userDto) {
        String jwt = JwtUtil.createJWT(userDto.getId().toString());
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        redisCache.setCacheObject(SystemConstants.APP_LOGIN_ID+userDto.getId(),loginUser,7,TimeUnit.DAYS);
        UserVo userVo = BeanCopyUtils.copyBean(userDto, UserVo.class);
        userVo.setToken(jwt);
        return ResponseResult.okResult(userVo);
    }
    private ResponseResult register(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userMapper.insert(user);
        userDto.setId(user.getId());
        return login(userDto);
    }
}
