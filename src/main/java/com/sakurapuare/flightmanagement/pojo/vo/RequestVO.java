package com.sakurapuare.flightmanagement.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

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

    private String createdAt;

    private String updatedAt;
}
