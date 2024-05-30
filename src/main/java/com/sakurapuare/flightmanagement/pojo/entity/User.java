package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "email")
    private String email;

    @TableField(value = "role")
    private int role;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
