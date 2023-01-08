package com.lx.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Rp)表实体类
 *
 * @author makejava
 * @since 2022-11-28 20:13:11
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_rp")
public class Rp  {
    @TableId
    private Long id;

    
    private Long rid;
    
    private String photo;



}
