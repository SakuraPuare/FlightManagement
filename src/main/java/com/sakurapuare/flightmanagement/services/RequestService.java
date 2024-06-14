package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.dto.RequestDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Request;
import jakarta.validation.Valid;

import java.util.List;

public interface RequestService {

    List<Request> getRequestByPagination(@Valid PaginationDTO paginationDTO);

    Request getRequestById(long id);

    void addRequest(@Valid RequestDTO requestDTO, long userId);

    void updateRequest(Request requests, @Valid RequestDTO requestDTO);

    void deleteRequest(Request request);

    void handleRequest(Request requests, long userId);

    long count();

}