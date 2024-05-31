package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Ticket {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "flight_id")
    private Long flightId;

    @TableField(value = "passenger_id")
    private Long passengerId;

    @TableField(value = "seat_class")
    private String seatClass;

    @TableField(value = "seat_number")
    private String seatNumber;

    @TableField(value = "status")
    private String status;

    @TableField(value = "price")
    private String price;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
