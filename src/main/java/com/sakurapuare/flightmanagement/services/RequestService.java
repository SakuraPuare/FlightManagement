package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.RequestDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Request;

import java.util.List;

public interface RequestService {

    List<Request> getRequestByPagination(int page, int count);

    Request getRequestById(long id);

    void addRequest(RequestDTO requestDTO, long userId);

    void updateRequest(Request requests, RequestDTO requestDTO);

    void deleteRequest(Request request);

    void handleRequest(Request requests, long userId);

    long count();

}