package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.GoodDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Good;

import java.util.List;

public interface GoodService {
    List<Good> getGoodsByPagination(PaginationDTO paginationDTO);

    List<Good> search(String query);

    Good getGoodByName(String name);

    Good findGoodById(Long id);

    Good addGood(GoodDTO goodDTO);

    boolean updateGood(Good good, GoodDTO goodDTO);

    boolean deleteGood(Long id);
}
