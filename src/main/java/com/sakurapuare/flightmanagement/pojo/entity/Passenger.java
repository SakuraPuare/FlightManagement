package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Passenger {
    @TableId(value = "passenger_id")
    private Long passengerId;

    @TableField(value = "user_id")
    private Long userId;
}
