package com.lx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Room)表实体类
 *
 * @author makejava
 * @since 2023-01-01 18:51:07
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_room")
public class Room  {
    //自习室id@TableId
    private Long id;

    //名字
    private String name;
    //详细地址
    private String address;
    //所在城市
    private String city;
    //归属人  商人id
    private Long mId;
    //状态(0启用 1禁用 2申请中 3待申请)
    private Integer state;

    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
    //价格
    private Double cost;
    //经度
    private Double longitude;
    //纬度
    private Double latitude;
    //所在区
    private String district;
    //空座位
    private Integer seat;
    //许可证图片
    private String licence;
    //座位图
    private String seatPhoto;
    //自习室图片
    private String photo;
    //自习室描述
    private String roomDescribe;
    //营业时间
    private String openingHours;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;



}
