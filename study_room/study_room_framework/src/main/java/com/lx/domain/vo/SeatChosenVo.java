package com.lx.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SeatChosenVo {
    private int seatNum;
    private Boolean chosen;
}
