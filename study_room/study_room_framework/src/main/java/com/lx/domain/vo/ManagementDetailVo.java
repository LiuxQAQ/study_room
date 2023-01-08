package com.lx.domain.vo;

import com.lx.domain.entity.Merchant;
import com.lx.domain.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagementDetailVo {
    private Room room;
    private Merchant merchant;
}
