package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.GoodsDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Goods;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsService {
    List<Goods> getGoodsByPagination(int page, int count);

    List<Goods> search(String query);

    Goods getGoodByName(String name);

    Goods getGoodById(long id);

    void addGood(GoodsDTO goodDTO, long userId);

    void updateGood(Goods good, GoodsDTO goodDTO);

    void deleteGood(long id);

    long count();

    BigDecimal getTotalPrice();

    void buyGood(Goods good);

    List<Goods> getGoodsByPaginationAndUserId(int page, int count, Long userId);
}
