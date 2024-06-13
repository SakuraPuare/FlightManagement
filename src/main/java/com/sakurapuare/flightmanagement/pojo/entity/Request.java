package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private Long handlerId;

    @TableField(value = "created_at")
    private String createdAt;

    @TableField(value = "updated_at")
    private String updatedAt;

}