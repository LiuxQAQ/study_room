package com.lx.service.impl;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.constants.SystemConstants;
import com.lx.utils.RedisCache;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

@Service
public class WXServiceImpl {
    @Autowired
    private RedisCache redisCache;

    public String wxDecrypt(String encryptData,String sessionId,String vi) throws Exception{
        // 开始解密
        String json = redisCache.getCacheObject(SystemConstants.WX_SESSION_ID + sessionId);
        JSONObject jsonObject = JSON.parseObject(json);
        String sessionKey = String.valueOf(jsonObject.get("session_key"));
        byte[] encData = Base64.decode(encryptData);
        byte[] iv = Base64.decode(vi);
        byte[] key = Base64.decode(sessionKey);


        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
//        Security.addProvider(new BouncyCastleProvider());

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
//        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
//        cipher.init(Cipher.DECRYPT_MODE,keySpec,parameters);
        byte[] bytes = cipher.doFinal(encData);
        return new String(bytes,"UTF-8");


//        KeyGenerator kgen = KeyGenerator.getInstance("AES");
//        kgen.init(128, new SecureRandom(key));
//        SecretKey secretKey = kgen.generateKey();
//        byte[] enCodeFormat = secretKey.getEncoded();
//        SecretKeySpec key1 = new SecretKeySpec(enCodeFormat, "AES");
//        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
//        cipher.init(Cipher.DECRYPT_MODE, key1);// 初始化
//        byte[] result = cipher.doFinal(encData);
//        return new String(result,"UTF-8");

    }
}
