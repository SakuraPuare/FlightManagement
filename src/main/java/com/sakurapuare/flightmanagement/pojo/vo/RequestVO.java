package com.sakurapuare.flightmanagement.pojo.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

@Data
public class RequestVO {

    private Long id;

    private Long userId;

    private String username;

    private String info;

    private String status = "unhandled";

    private Long handlerId;

    private String handlerName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
