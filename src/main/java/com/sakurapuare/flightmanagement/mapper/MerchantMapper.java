package com.sakurapuare.flightmanagement.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakurapuare.flightmanagement.pojo.entity.user.Merchant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {

    default Merchant findMerchantByMerchantName(String merchantName) {
        return this.selectOne(
                new QueryWrapper<Merchant>().eq("merchant_name", merchantName));
    }

}
