package com.lx.domain.entity;

import lombok.Data;

@Data
public class WXAuth {
    private String encryptedData;
    private String iv;
    private String sessionId;
}
