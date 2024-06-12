package com.sakurapuare.flightmanagement.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Airline {
    @TableId(value = "airline_id")
    private Long airlineId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "airline_name")
    private String airlineName;

    @TableField(value = "airline_country")
    private String airlineCountry;

    @TableField(value = "airline_code")
    private String airlineCode;

    @TableField(value = "airline_description")
    private String airlineDescription;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
