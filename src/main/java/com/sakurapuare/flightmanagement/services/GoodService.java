package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.GoodDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Good;
import jakarta.validation.Valid;

import java.util.List;

public interface GoodService {
    List<Good> getGoodsByPagination(PaginationDTO paginationDTO);

    List<Good> search(String query);

    Good getGoodByName(String name);

    Good findGoodById(@Valid Long id);

    Good addGood(@Valid GoodDTO goodDTO);

    boolean updateGood(@Valid Good good, @Valid GoodDTO goodDTO);

    boolean deleteGood(@Valid Long id);
}
