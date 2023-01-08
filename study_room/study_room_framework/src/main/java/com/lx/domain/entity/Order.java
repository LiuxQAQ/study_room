package com.lx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Order)表实体类
 *
 * @author makejava
 * @since 2022-11-20 09:36:48
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_order")
public class Order  {
    @TableId
    private Long id;

    //用户id
    private Long uid;
    //自习室id
    private Long rid;
    //座位
    private Integer seat;
    //预约时间
    private Date datetime;

    private Date startTime;

    private Date endTime;
    //状态
    private Integer state;

    private Integer totalPrice;
    //订单创建时间
    private Date createTime;
    
    private Long createBy;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

    private String code;



}
