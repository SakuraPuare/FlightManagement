package com.sakurapuare.flightmanagement.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "merchants")
public class Merchant {
    @TableId(value = "merchant_id", type = IdType.AUTO)
    private Long merchantId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "merchant_name")
    private String merchantName;

    @TableField(value = "merchant_address")
    private String merchantAddress;

    @TableField(value = "merchant_description")
    private String merchantDescription;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
