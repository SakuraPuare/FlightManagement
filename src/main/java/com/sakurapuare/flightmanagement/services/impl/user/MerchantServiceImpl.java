package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.user.MerchantMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Merchant;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.MerchantInfo;
import com.sakurapuare.flightmanagement.services.user.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantMapper merchantMapper;

    public MerchantServiceImpl(MerchantMapper merchantMapper) {
        this.merchantMapper = merchantMapper;
    }

    @Override
    public Merchant getMerchantByMerchantName(String merchantName) {
        return merchantMapper.selectOne(
                new QueryWrapper<Merchant>()
                        .eq("merchant_name", merchantName));
    }

    @Override
    public void register(long userId, UserRegisterDTO<MerchantInfo> merchantInfoUserRegisterDTO) {
        Merchant merchant = getMerchantByMerchantName(
                merchantInfoUserRegisterDTO.getData().getMerchantName());
        if (merchant != null) {
            return;
        }

        merchant = new Merchant();
        merchant.setUserId(userId);
        BeanUtils.copyProperties(merchantInfoUserRegisterDTO.getData(), merchant);
        merchantMapper.insert(merchant);

    }

    @Override
    public long count() {
        return merchantMapper.selectCount(null);
    }

    @Override
    public Merchant getMerchantById(Long id) {
        return merchantMapper.selectById(id);
    }

    @Override
    public List<Merchant> getMerchantsByPagination(int page, int count) {
        Page<Merchant> merchantPage = new Page<>(page, count);
        return merchantMapper.selectPage(merchantPage, null).getRecords();
    }
}
