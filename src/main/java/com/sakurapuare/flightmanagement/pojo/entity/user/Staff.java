package com.sakurapuare.flightmanagement.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "staffs")
public class Staff {
    @TableId(value = "staff_id", type = IdType.AUTO)
    private Long staffId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "staff_name")
    private String staffName;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
