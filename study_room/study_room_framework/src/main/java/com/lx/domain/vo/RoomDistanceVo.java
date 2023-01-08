package com.lx.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Data
@Accessors(chain = true)
public class RoomDistanceVo implements Comparable<RoomDistanceVo>{
    private Long id;
    //名字
    private String name;
    private Double longitude;

    private Double latitude;

    //所在城市
    private String city;
    //所在区
    private String district;

    private Double distance;

    private String photo;
    private String openingHours;

    @Override
    public int compareTo(@NotNull RoomDistanceVo o) {
        return this.getDistance().intValue()-o.getDistance().intValue();
    }
}
