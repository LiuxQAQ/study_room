package com.lx.domain.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class VisualVo {
    private String date;
    private Integer nums;
    private Integer totalPrices;
}
