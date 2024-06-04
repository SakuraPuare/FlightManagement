package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakurapuare.flightmanagement.mapper.user.MerchantMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Merchant;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.MerchantInfo;
import com.sakurapuare.flightmanagement.services.user.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantMapper merchantMapper;

    public MerchantServiceImpl(MerchantMapper merchantMapper) {
        this.merchantMapper = merchantMapper;
    }

    @Override
    public Merchant findMerchantByMerchantName(String merchantName) {
        return merchantMapper.selectOne(
                new QueryWrapper<Merchant>()
                        .eq("merchant_name", merchantName));
    }

    @Override
    public Merchant register(Long userId, UserRegisterDTO<MerchantInfo> merchantInfoUserRegisterDTO) {
        Merchant merchant = findMerchantByMerchantName(
                merchantInfoUserRegisterDTO.getData().getMerchantName());
        if (merchant != null) {
            return merchant;
        }

        merchant = new Merchant();
        merchant.setUserId(userId);
        BeanUtils.copyProperties(merchantInfoUserRegisterDTO.getData(), merchant);
        merchantMapper.insert(merchant);

        return merchant;
    }
}
