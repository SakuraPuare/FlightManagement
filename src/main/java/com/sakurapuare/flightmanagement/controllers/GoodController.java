package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.mapper.GoodMapper;
import com.sakurapuare.flightmanagement.pojo.dto.GoodDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Good;
import com.sakurapuare.flightmanagement.services.GoodService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/good")
@Tag(name = "Good Controller", description = "Good API Endpoints")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private GoodMapper goodMapper;

    @GetMapping("/list")
    public Response<List<Good>> getGoodList(@Valid @RequestBody PaginationDTO paginationDTO) {
        return Response.success(goodService.getGoodsByPagination(paginationDTO));
    }

    @GetMapping("/{id}")
    public Response<Good> getGoodById(@Valid @PathVariable(name = "id") Long id) {
        return Response.success(goodMapper.selectById(id));
    }

    @GetMapping("/search")
    public Response<List<Good>> searchGood(@RequestParam(name = "query") String query) {
        return Response.success(goodService.search(query));
    }

    @PostMapping("/")
    public Response<Void> addGood(@Valid @RequestBody GoodDTO goodDTO) {
        if (goodService.getGoodByName(goodDTO.getName()) != null) {
            return Response.error("Good already exists");
        }

        Good good = new Good();
        BeanUtils.copyProperties(goodDTO, good);
        goodMapper.insert(good);
        return Response.success("Good added successfully");
    }

    @PutMapping("/{id}")
    public Response<Void> updateGood(@Valid @PathVariable(name = "id") Long id,
            @Valid @RequestBody GoodDTO goodDTO) {
        Good good = goodMapper.selectById(id);
        if (good == null) {
            return Response.error("Good not found");
        }
        BeanUtils.copyProperties(goodDTO, good);
        goodMapper.updateById(good);
        return Response.success("Good updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteGood(@Valid @PathVariable(name = "id") Long id) {
        goodMapper.deleteById(id);
        return Response.success("Good deleted successfully");
    }
}
