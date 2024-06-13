package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.GoodsDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Goods;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsService {
    List<Goods> getGoodsByPagination(PaginationDTO paginationDTO);

    List<Goods> search(String query);

    Goods getGoodByName(String name);

    Goods getGoodById(long id);

    void addGood(GoodsDTO goodDTO);

    void updateGood(Goods good, GoodsDTO goodDTO);

    void deleteGood(long id);

    long count();

    BigDecimal getTotalPrice();
}
