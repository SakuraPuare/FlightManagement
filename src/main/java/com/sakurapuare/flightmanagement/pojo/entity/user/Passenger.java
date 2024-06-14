package com.sakurapuare.flightmanagement.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "passengers")
public class Passenger {
    @TableId(value = "passenger_id", type = IdType.AUTO)
    private Long passengerId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "passenger_name")
    private String passengerName;

    @TableField(value = "passenger_phone")
    private String passengerPhone;

    @TableField(value = "identity_number")
    private String identityNumber;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
