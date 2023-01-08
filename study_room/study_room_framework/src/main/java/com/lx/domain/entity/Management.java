package com.lx.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

/**
 * (Management)表实体类
 *
 * @author makejava
 * @since 2022-11-20 16:49:35
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_management")
public class Management  {
    @TableId
    private Long id;

    //商家id
    private Long mid;
    //自习室id
    private Long rid;
    //状态
    private Integer state;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;



    //====================
    @TableField(exist = false)
    private String merchantName;
    @TableField(exist = false)
    private String roomName;



}
