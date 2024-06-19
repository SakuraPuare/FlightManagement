package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName(value = "tickets")
public class Ticket {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "flight_id")
    private Long flightId;

    @TableField(value = "seat_class")
    private String seatClass;

    @TableField(value = "quota")
    private Long quota;

    @TableField(value = "price")
    private BigDecimal price;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
