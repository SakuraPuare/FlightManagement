package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "luggages")
public class Luggage {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "order_id")
    private Long orderId;

    @TableField(value = "weight")
    private Double weight;

    @TableField(value = "staff_id")
    private Long staffId;

    @TableField(value = "created_at")
    private String createdAt;

    @TableField(value = "updated_at")
    private String updatedAt;
}
