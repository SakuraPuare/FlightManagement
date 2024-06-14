package com.sakurapuare.flightmanagement.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "airlines")
public class Airline {
    @TableId(value = "airline_id", type = IdType.AUTO)
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
