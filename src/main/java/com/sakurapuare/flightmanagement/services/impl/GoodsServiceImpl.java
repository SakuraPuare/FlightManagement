package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.GoodsMapper;
import com.sakurapuare.flightmanagement.pojo.dto.GoodsDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Goods;
import com.sakurapuare.flightmanagement.services.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsMapper goodMapper;

    public GoodsServiceImpl(GoodsMapper goodMapper) {
        this.goodMapper = goodMapper;
    }

    @Override
    public List<Goods> getGoodsByPagination(PaginationDTO paginationDTO) {
        Page<Goods> page = new Page<>(paginationDTO.getPage(), paginationDTO.getCount());
        return goodMapper.selectPage(page, null).getRecords();
    }

    @Override
    public List<Goods> search(String query) {
        Set<Goods> goods = new HashSet<>();
        goods.addAll(goodMapper.selectList(new QueryWrapper<Goods>().like("name", query)));
        goods.addAll(goodMapper.selectList(new QueryWrapper<Goods>().like("description", query)));
        goods.addAll(goodMapper.selectList(new QueryWrapper<Goods>().like("category", query)));
        return List.copyOf(goods);
    }

    @Override
    public Goods getGoodByName(String name) {
        return goodMapper.selectOne(new QueryWrapper<Goods>().eq("name", name));
    }

    @Override
    public Goods getGoodById(long id) {
        return goodMapper.selectById(id);
    }

    @Override
    public void addGood(GoodsDTO goodDTO) {
        Goods good = new Goods();
        BeanUtils.copyProperties(goodDTO, good);
        goodMapper.insert(good);
    }

    @Override
    public void updateGood(Goods good, GoodsDTO goodDTO) {
        BeanUtils.copyProperties(goodDTO, good);
        goodMapper.updateById(good);

    }

    @Override
    public void deleteGood(long id) {
        goodMapper.deleteById(id);
    }

    @Override
    public long count() {
        return goodMapper.selectCount(null);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return goodMapper.selectList(null)
                .stream()
                .map(Goods::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}