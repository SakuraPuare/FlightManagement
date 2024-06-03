package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.mapper.GoodMapper;
import com.sakurapuare.flightmanagement.pojo.dto.GoodDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Good;
import com.sakurapuare.flightmanagement.services.GoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GoodControllerTest {

    @Mock
    private GoodService goodService;

    @Mock
    private GoodMapper goodMapper;

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

        assertEquals(Response.success(expectedGoods), response);
        verify(goodService, times(1)).getGoodsByPagination(paginationDTO);
    }

    @Test
    void testGetGoodById() {
        Long id = 1L;
        Good expectedGood = new Good();
        when(goodMapper.selectById(id)).thenReturn(expectedGood);

        Response<Good> response = goodController.getGoodById(id);

        assertEquals(Response.success(expectedGood), response);
        verify(goodMapper, times(1)).selectById(id);
    }

    @Test
    void testSearchGood() {
        String query = "example";
        List<Good> expectedGoods = new ArrayList<>();
        when(goodService.search(query)).thenReturn(expectedGoods);

        Response<List<Good>> response = goodController.searchGood(query);

        assertEquals(Response.success(expectedGoods), response);
        verify(goodService, times(1)).search(query);
    }

    @Test
    void testAddGood() {
        GoodDTO goodDTO = new GoodDTO();
        goodDTO.setName("Example Good");

        Good existingGood = new Good();
        existingGood.setName("Example Good");
        when(goodService.getGoodByName(goodDTO.getName())).thenReturn(existingGood);

        Response<Void> response = goodController.addGood(goodDTO);

        assertEquals(Response.error("Good already exists"), response);
        verify(goodService, times(1)).getGoodByName(goodDTO.getName());
        verify(goodMapper, never()).insert(any(Good.class));
    }

    @Test
    void testAddGood_Success() {
        GoodDTO goodDTO = new GoodDTO();
        goodDTO.setName("New Good");

        when(goodService.getGoodByName(goodDTO.getName())).thenReturn(null);

        Good good = new Good();
        BeanUtils.copyProperties(goodDTO, good);
        doAnswer(invocation -> null).when(goodMapper).insert(good);

        Response<Void> response = goodController.addGood(goodDTO);

        assertEquals(Response.success("Good added successfully"), response);
        verify(goodService, times(1)).getGoodByName(goodDTO.getName());
        verify(goodMapper, times(1)).insert(good);
    }

    @Test
    void testUpdateGood_Success() {
        Long id = 1L;
        GoodDTO goodDTO = new GoodDTO();
        Good existingGood = new Good();
        when(goodMapper.selectById(id)).thenReturn(existingGood);

        Good updatedGood = new Good();
        BeanUtils.copyProperties(goodDTO, updatedGood);
        doAnswer(invocation -> null).when(goodMapper).updateById(updatedGood);

        Response<Void> response = goodController.updateGood(id, goodDTO);

        assertEquals(Response.success("Good updated successfully"), response);
        // verify(goodMapper, times(2)).selectById(id);
        // verify(goodMapper, times(2)).updateById(updatedGood);
    }

    @Test
    void testUpdateGood_NotFound() {
        Long id = 1L;
        GoodDTO goodDTO = new GoodDTO();
        when(goodMapper.selectById(id)).thenReturn(null);

        Response<Void> response = goodController.updateGood(id, goodDTO);

        assertEquals(Response.error("Good not found"), response);
        verify(goodMapper, times(1)).selectById(id);
        verify(goodMapper, never()).updateById(any(Good.class));
    }

    @Test
    void testDeleteGood() {
        Long id = 1L;

        Response<Void> response = goodController.deleteGood(id);

        assertEquals(Response.success("Good deleted successfully"), response);
        verify(goodMapper, times(1)).deleteById(id);
    }
}