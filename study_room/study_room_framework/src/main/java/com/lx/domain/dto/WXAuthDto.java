package com.lx.domain.dto;

import lombok.Data;

@Data
public class WXAuthDto {
    private String encryptedData;
    private String iv;
    private String sessionId;
}
