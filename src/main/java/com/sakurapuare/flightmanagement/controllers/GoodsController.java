package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.GoodsDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Goods;
import com.sakurapuare.flightmanagement.services.GoodsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/goods")
@Tag(name = "Goods Controller", description = "Good API Endpoints")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/list")
    public Response<List<Goods>> getGoodList(@RequestParam("page") int page, @RequestParam("count") int count) {

        return Response.success(goodsService.getGoodsByPagination(page, count));
    }

    @GetMapping("/my")
    public Response<List<Goods>> getMyGoodList(@RequestParam("page") int page, @RequestParam("count") int count,
                                               HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        return Response.success(goodsService.getGoodsByPaginationAndUserId(page, count, userId));
    }

    @GetMapping("/{id}")
    public Response<Goods> getGoodById(@PathVariable(name = "id") long id) {
        Goods good = goodsService.getGoodById(id);
        if (good == null) {
            return Response.error("Good not found");
        }
        return Response.success(good);
    }

    @GetMapping("/search")
    public Response<List<Goods>> searchGood(@RequestParam(name = "query") String query) {
        return Response.success(goodsService.search(query));
    }

    @Transactional
    @PostMapping("/{id}/buy")
    public Response<String> buyGood(@PathVariable(name = "id") long id) {
        Goods good = goodsService.getGoodById(id);
        if (good == null) {
            return Response.error("Good not found");
        }
        if (good.getStock() <= 0) {
            return Response.error("Good out of stock");
        }

        goodsService.buyGood(good);
        return Response.success("Good bought successfully");
    }

    @PostMapping("/")
    public Response<String> addGood(@Valid @RequestBody GoodsDTO goodDTO, HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        if (goodsService.getGoodByName(goodDTO.getName()) != null) {
            return Response.error("Good already exists");
        }

        goodsService.addGood(goodDTO, userId);
        return Response.success("Good added successfully");
    }

    @PutMapping("/{id}")
    public Response<String> updateGood(@PathVariable(name = "id") long id,
                                       @RequestBody GoodsDTO goodDTO) {
        Goods good = goodsService.getGoodById(id);
        if (good == null) {
            return Response.error("Good not found");
        }
        goodsService.updateGood(good, goodDTO);
        return Response.success("Good updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<String> deleteGood(@PathVariable(name = "id") long id) {
        goodsService.deleteGood(id);
        return Response.success("Good deleted successfully");
    }
}
