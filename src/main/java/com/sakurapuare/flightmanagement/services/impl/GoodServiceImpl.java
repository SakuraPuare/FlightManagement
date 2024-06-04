package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.GoodMapper;
import com.sakurapuare.flightmanagement.pojo.dto.GoodDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Good;
import com.sakurapuare.flightmanagement.services.GoodService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GoodServiceImpl implements GoodService {

    private final GoodMapper goodMapper;

    public GoodServiceImpl(GoodMapper goodMapper) {
        this.goodMapper = goodMapper;
    }

    @Override
    public List<Good> getGoodsByPagination(PaginationDTO paginationDTO) {
        Page<Good> page = new Page<>(paginationDTO.getPage(), paginationDTO.getCount());
        return goodMapper.selectPage(page, null).getRecords();
    }

    @Override
    public List<Good> search(String query) {
        Set<Good> goods = new HashSet<>();
        goods.addAll(goodMapper.selectList(new QueryWrapper<Good>().like("name", query)));
        goods.addAll(goodMapper.selectList(new QueryWrapper<Good>().like("description", query)));
        goods.addAll(goodMapper.selectList(new QueryWrapper<Good>().like("category", query)));
        return List.copyOf(goods);
    }

    @Override
    public Good getGoodByName(String name) {
        return goodMapper.selectOne(new QueryWrapper<Good>().eq("name", name));
    }

    @Override
    public Good findGoodById(Long id) {
        return goodMapper.selectById(id);
    }

    @Override
    public Good addGood(GoodDTO goodDTO) {
        Good good = new Good();
        BeanUtils.copyProperties(goodDTO, good);
        goodMapper.insert(good);
        return good;
    }

    @Override
    public boolean updateGood(Good good, GoodDTO goodDTO) {
        BeanUtils.copyProperties(goodDTO, good);
        return goodMapper.updateById(good) == 1;

    }

    @Override
    public boolean deleteGood(Long id) {
        return goodMapper.deleteById(id) == 1;
    }
}