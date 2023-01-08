package com.lx.service;

import com.lx.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface OssUploadService {
    ResponseResult uploadImg(MultipartFile img);
}
