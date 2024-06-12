package com.sakurapuare.flightmanagement.services.user;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Merchant;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.MerchantInfo;

public interface MerchantService {

    Merchant getMerchantByMerchantName(String merchantName);

    void register(long userId, UserRegisterDTO<MerchantInfo> merchantInfoUserRegisterDTO);

}
