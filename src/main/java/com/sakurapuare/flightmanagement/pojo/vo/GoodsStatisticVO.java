package com.sakurapuare.flightmanagement.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsStatisticVO {
    private long goodsCount;

    private BigDecimal goodsTotalPrice;
}
