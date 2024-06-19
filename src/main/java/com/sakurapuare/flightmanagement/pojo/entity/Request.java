package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "requests")
public class Request {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "info")
    private String info;

    @TableField(value = "status")
    private String status = "unhandled";

    @TableField(value = "handler_id")
    private Long handlerId = null;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private String createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private String updatedAt;

}
