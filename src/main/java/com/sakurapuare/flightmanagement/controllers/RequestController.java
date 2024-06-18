package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.RequestDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Request;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import com.sakurapuare.flightmanagement.pojo.vo.RequestVO;
import com.sakurapuare.flightmanagement.services.RequestService;
import com.sakurapuare.flightmanagement.services.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/requests")
@Tag(name = "Request Controller", description = "Request API Endpoints")
public class RequestController {

    private final RequestService requestService;

    private final UserService userService;

    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public Response<List<RequestVO>> getRequestList(@RequestParam("page") int page, @RequestParam("count") int count) {
        List<Request> requestList = requestService.getRequestByPagination(page, count);

        Map<Long, User> userMap = new HashMap<>();
        List<RequestVO> requestVOList = new ArrayList<>();
        for (Request request : requestList) {
            User user = userMap.computeIfAbsent(request.getUserId(), id -> userService.getUserById(id));

            User handler = null;
            if (request.getHandlerId() != null) {
                handler = userMap.computeIfAbsent(request.getHandlerId(), id -> userService.getUserById(id));
            }

            RequestVO requestVO = new RequestVO();
            BeanUtils.copyProperties(request, requestVO);
            BeanUtils.copyProperties(user, requestVO);
            if (handler != null) {
                requestVO.setHandlerName(handler.getUsername());
            }

            requestVOList.add(requestVO);
        }

        return Response.success(requestVOList);
    }

    @GetMapping("/{id}")
    public Response<RequestVO> getRequestById(@PathVariable(name = "id") long id) {
        Request request = requestService.getRequestById(id);
        if (request == null) {
            return Response.error("Request not found");
        }

        User user = userService.getUserById(request.getUserId());
        User handler = null;
        if (request.getHandlerId() != null) {
            handler = userService.getUserById(request.getHandlerId());
        }
        RequestVO requestVO = new RequestVO();
        BeanUtils.copyProperties(request, requestVO);
        BeanUtils.copyProperties(user, requestVO);
        if (handler != null) {
            requestVO.setHandlerName(handler.getUsername());
        }

        return Response.success(requestVO);
    }

    @PostMapping("/")
    public Response<Void> addRequest(@Valid @RequestBody RequestDTO requestDTO, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        requestService.addRequest(requestDTO, userId);
        return Response.success("Request added");
    }

    @PutMapping("/{id}")
    public Response<Void> updateRequest(@PathVariable(name = "id") long id, @RequestBody RequestDTO requestDTO) {
        Request requests = requestService.getRequestById(id);
        if (requests == null) {
            return Response.error("Request not found");
        }

        requestService.updateRequest(requests, requestDTO);
        return Response.success("Request updated");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteRequest(@PathVariable(name = "id") long id) {
        Request request = requestService.getRequestById(id);
        if (request == null) {
            return Response.error("Request not found");
        }

        requestService.deleteRequest(request);
        return Response.success("Request deleted");
    }

    @PostMapping("/{id}/handle")
    public Response<Void> handleRequest(@PathVariable(name = "id") long id, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        Request requests = requestService.getRequestById(id);
        if (requests == null) {
            return Response.error("Request not found");
        }

        requestService.handleRequest(requests, userId);
        return Response.success("Request handled");
    }

    @PostMapping("/{id}/resolve")
    public Response<Void> resolveRequest(@PathVariable(name = "id") long id, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        Request requests = requestService.getRequestById(id);
        if (requests == null) {
            return Response.error("Request not found");
        }

        requestService.resolveRequest(requests, userId);
        return Response.success("Request resolved");
    }

    @PostMapping("/{id}/cancel")
    public Response<Void> cancelRequest(@PathVariable(name = "id") long id, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        Request requests = requestService.getRequestById(id);
        if (requests == null) {
            return Response.error("Request not found");
        }

        requestService.cancelRequest(requests, userId);
        return Response.success("Request canceled");
    }
}
