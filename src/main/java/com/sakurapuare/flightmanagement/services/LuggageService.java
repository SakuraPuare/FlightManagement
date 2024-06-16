package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.LuggageDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Luggage;

import java.util.List;

public interface LuggageService {

    List<Luggage> getLuggageByPagination(int page, int count);

    List<Luggage> getLuggageByPaginationAndId(int page, int count, long userId);

    Luggage getLuggageByIdAndUserId(long id, long userId);

    void addLuggage(LuggageDTO luggageDTO, long userId);

    void updateLuggage(Luggage luggage, LuggageDTO luggageDTO, long userId);

    void deleteLuggage(long id, long userId);

    long count();

}