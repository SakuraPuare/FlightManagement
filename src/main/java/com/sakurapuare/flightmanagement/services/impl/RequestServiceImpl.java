package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.RequestMapper;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.dto.RequestDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Request;
import com.sakurapuare.flightmanagement.services.RequestService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestMapper requestMapper;

    public RequestServiceImpl(RequestMapper requestMapper) {
        this.requestMapper = requestMapper;
    }

    @Override
    public List<Request> getRequestByPagination(@Valid PaginationDTO paginationDTO) {
        Page<Request> page = new Page<>(paginationDTO.getPage(), paginationDTO.getCount());
        return requestMapper.selectPage(page, null).getRecords();
    }

    @Override
    public Request getRequestById(long id) {
        return requestMapper.selectById(id);
    }

    @Override
    public void addRequest(@Valid RequestDTO requestDTO, long userId) {
        Request request = new Request();
        BeanUtils.copyProperties(requestDTO, request);
        request.setUserId(userId);
        requestMapper.insert(request);
    }

    @Override
    public void updateRequest(Request requests, @Valid RequestDTO requestDTO) {
        BeanUtils.copyProperties(requestDTO, requests);
        requestMapper.updateById(requests);
    }

    @Override
    public void deleteRequest(Request request) {
        requestMapper.deleteById(request);
    }

    @Override
    public void handleRequest(Request requests, long userId) {
        requests.setHandlerId(userId);
        requests.setStatus("handled");
        requestMapper.updateById(requests);
    }

    @Override
    public long count() {
        return requestMapper.selectCount(null);
    }

}
