package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.GoodsMapper;
import com.sakurapuare.flightmanagement.pojo.dto.GoodsDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Goods;
import com.sakurapuare.flightmanagement.pojo.entity.user.Merchant;
import com.sakurapuare.flightmanagement.services.GoodsService;
import com.sakurapuare.flightmanagement.services.user.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsMapper goodsMapper;

    private final MerchantService merchantService;

    public GoodsServiceImpl(GoodsMapper goodMapper, MerchantService merchantService) {
        this.goodsMapper = goodMapper;
        this.merchantService = merchantService;
    }

    @Override
    public List<Goods> getGoodsByPagination(int page, int count) {
        Page<Goods> pagination = new Page<>(page, count);
        return goodsMapper.selectPage(pagination, null).getRecords();
    }

    @Override
    public List<Goods> search(String query) {
        Set<Goods> goods = new HashSet<>();
        goods.addAll(goodsMapper.selectList(new QueryWrapper<Goods>().like("name", query)));
        goods.addAll(goodsMapper.selectList(new QueryWrapper<Goods>().like("description", query)));
        goods.addAll(goodsMapper.selectList(new QueryWrapper<Goods>().like("category", query)));
        return List.copyOf(goods);
    }

    @Override
    public Goods getGoodByName(String name) {
        return goodsMapper.selectOne(new QueryWrapper<Goods>().eq("name", name));
    }

    @Override
    public Goods getGoodById(long id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public void addGood(GoodsDTO goodDTO, long userId) {
        Goods good = new Goods();
        BeanUtils.copyProperties(goodDTO, good);
        good.setMerchantId(merchantService.getMerchantByUserId(userId).getMerchantId());
        goodsMapper.insert(good);
    }

    @Override
    public void updateGood(Goods good, GoodsDTO goodDTO) {
        BeanUtils.copyProperties(goodDTO, good);
        goodsMapper.updateById(good);

    }

    @Override
    public void deleteGood(long id) {
        goodsMapper.deleteById(id);
    }

    @Override
    public long count() {
        return goodsMapper.selectCount(null);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return goodsMapper.selectList(null)
                .stream()
                .map(Goods::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void buyGood(Goods good) {
        good.setStock(good.getStock() - 1);
        goodsMapper.updateById(good);
    }

    @Override
    public List<Goods> getGoodsByPaginationAndUserId(int page, int count, Long userId) {
        Merchant merchant = merchantService.getMerchantByUserId(userId);
        return goodsMapper.selectPage(new Page<>(page, count),
                new QueryWrapper<Goods>().eq("merchant_id", merchant.getMerchantId())).getRecords();
    }
}