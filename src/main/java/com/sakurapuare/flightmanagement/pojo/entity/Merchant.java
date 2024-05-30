package com.sakurapuare.flightmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Merchant {
    @TableId(value = "merchant_id")
    private Long merchantId;

    @TableField(value = "user_id")
    private Long userId;
}
