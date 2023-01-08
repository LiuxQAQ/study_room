package com.lx.domain.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.language.bm.Rule;

import java.util.Date;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-10-30 21:45:55
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
public class UserVo {
    //主键@TableId
    private Long id;
    //电话
    private Long phone;
    //邮箱
    private String email;
    //角色
    private Integer role;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
    //(0代表启用，1代表禁用)
    private Integer state;
    //用户名
    private String userName;
    //头像
    private String portrait;
    //昵称
    private String nickName;
    //性别
    private String gender;
    
    private String city;
    //省
    private String province;
    //国
    private String country;

    private String token;



}
