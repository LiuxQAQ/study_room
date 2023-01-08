package com.lx.domain.entity;


import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Seat)表实体类
 *
 * @author makejava
 * @since 2022-11-20 09:47:18
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_seat")
public class Seat  {
    @TableId
    private Long id;

    //自习室id
    private Long rid;
    //座位序号
    private Integer seat;

    private String datetime;

    private Date startTime;

    private Date endTime;



}
