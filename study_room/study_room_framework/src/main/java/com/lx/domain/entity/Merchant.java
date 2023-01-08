package com.lx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Merchant)表实体类
 *
 * @author makejava
 * @since 2022-11-20 10:50:52
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_merchant")
public class Merchant  {

    //商家id@TableId
    private Long id;

    //电话
    private Long phone;
    //邮箱
    private String email;
    
    private String name;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
    //(0代表启用，1代表禁用)
    private Integer state;



}
