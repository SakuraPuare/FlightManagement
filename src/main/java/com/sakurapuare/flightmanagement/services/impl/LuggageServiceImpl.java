package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.LuggageMapper;
import com.sakurapuare.flightmanagement.pojo.dto.LuggageDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Luggage;
import com.sakurapuare.flightmanagement.services.LuggageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuggageServiceImpl implements LuggageService {

    private final LuggageMapper luggageMapper;

    public LuggageServiceImpl(LuggageMapper luggageMapper) {
        this.luggageMapper = luggageMapper;
    }

    @Override
    public List<Luggage> getLuggageByPagination(int page, int count) {
        Page<Luggage> pagination = new Page<>(page, count);
        return luggageMapper.selectPage(pagination, null).getRecords();
    }

    @Override
    public List<Luggage> getLuggageByPaginationAndUserId(int page, int count, long userId) {
        Page<Luggage> pagination = new Page<>(page, count);
        return luggageMapper.selectPage(pagination, new QueryWrapper<Luggage>()
                .eq("user_id", userId)).getRecords();
    }

    @Override
    public Luggage getLuggageByIdAndUserId(long id, long userId) {
        return luggageMapper.selectOne(new QueryWrapper<Luggage>()
                .eq("id", id)
                .eq("user_id", userId));
    }

    @Override
    public void addLuggage(LuggageDTO luggageDTO, long userId) {
        Luggage luggage = new Luggage();
        luggage.setStaffId(userId);
        BeanUtils.copyProperties(luggageDTO, luggage);
        luggageMapper.insert(luggage);
    }

    @Override
    public void updateLuggage(Luggage luggage, LuggageDTO luggageDTO, long userId) {
        luggage.setStaffId(userId);
        BeanUtils.copyProperties(luggageDTO, luggage);
        luggageMapper.updateById(luggage);
    }

    @Override
    public void deleteLuggage(long id, long userId) {
        luggageMapper.delete(new QueryWrapper<Luggage>()
                .eq("id", id)
                .eq("user_id", userId));
    }

    @Override
    public long count() {
        return luggageMapper.selectCount(null);
    }

}
