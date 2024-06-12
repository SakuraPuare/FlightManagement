package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.GoodDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Good;
import com.sakurapuare.flightmanagement.services.GoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GoodControllerTest {

    @Mock
    private GoodService goodService;

    @InjectMocks
    private GoodController goodController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGoodList() {
        PaginationDTO paginationDTO = new PaginationDTO();
        List<Good> expectedGoods = new ArrayList<>();
        when(goodService.getGoodsByPagination(paginationDTO)).thenReturn(expectedGoods);

        Response<List<Good>> response = goodController.getGoodList(paginationDTO);

        assertEquals(expectedGoods, response.getData());
    }

    @Test
    void testGetGoodById() {
        long id = 1L;
        Good expectedGood = new Good();
        when(goodService.getGoodById(id)).thenReturn(expectedGood);

        Response<Good> response = goodController.getGoodById(id);

        assertEquals(expectedGood, response.getData());
    }

    @Test
    void testSearchGood() {
        String query = "example";
        List<Good> expectedGoods = new ArrayList<>();
        when(goodService.search(query)).thenReturn(expectedGoods);

        Response<List<Good>> response = goodController.searchGood(query);

        assertEquals(expectedGoods, response.getData());
    }

    @Test
    void testAddGood() {
        GoodDTO goodDTO = new GoodDTO();
        when(goodService.getGoodByName(goodDTO.getName())).thenReturn(null);

        Response<Void> response = goodController.addGood(goodDTO);

        assertEquals(Response.success("Good added successfully"), response);
        verify(goodService, times(1)).addGood(goodDTO);
    }

    @Test
    void testUpdateGood() {
        long id = 1L;
        GoodDTO goodDTO = new GoodDTO();
        Good existingGood = new Good();
        when(goodService.getGoodById(id)).thenReturn(existingGood);

        Response<Void> response = goodController.updateGood(id, goodDTO);

        assertEquals(Response.success("Good updated successfully"), response);
        verify(goodService, times(1)).updateGood(existingGood, goodDTO);
    }

    @Test
    void testDeleteGood() {
        long id = 1L;

        Response<Void> response = goodController.deleteGood(id);

        assertEquals(Response.success("Good deleted successfully"), response);
        verify(goodService, times(1)).deleteGood(id);
    }
}