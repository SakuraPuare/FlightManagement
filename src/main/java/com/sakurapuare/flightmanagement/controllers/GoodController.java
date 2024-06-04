package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.GoodDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Good;
import com.sakurapuare.flightmanagement.services.GoodService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/goods")
@Tag(name = "Good Controller", description = "Good API Endpoints")
public class GoodController {

    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("/list")
    public Response<List<Good>> getGoodList(@Valid @RequestBody PaginationDTO paginationDTO) {
        return Response.success(goodService.getGoodsByPagination(paginationDTO));
    }

    @GetMapping("/{id}")
    public Response<Good> getGoodById(@Valid @PathVariable(name = "id") Long id) {
        Good good = goodService.findGoodById(id);
        if (good == null) {
            return Response.error("Good not found");
        }
        return Response.success(good);
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

        goodService.addGood(goodDTO);
        return Response.success("Good added successfully");
    }

    @PutMapping("/{id}")
    public Response<Void> updateGood(@Valid @PathVariable(name = "id") Long id,
                                     @Valid @RequestBody GoodDTO goodDTO) {
        Good good = goodService.findGoodById(id);
        if (good == null) {
            return Response.error("Good not found");
        }
        goodService.updateGood(good, goodDTO);
        return Response.success("Good updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteGood(@Valid @PathVariable(name = "id") Long id) {
        goodService.deleteGood(id);
        return Response.success("Good deleted successfully");
    }
}
