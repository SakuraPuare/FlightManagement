package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Airline {
    @TableId(value = "airline_id")
    private Long airlineId;

    @TableField(value = "user_id")
    private Long userId;
}
