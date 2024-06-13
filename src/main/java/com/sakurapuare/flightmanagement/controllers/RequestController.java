package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.dto.RequestDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Request;
import com.sakurapuare.flightmanagement.services.RequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/requests")
@Tag(name = "Request Controller", description = "Request API Endpoints")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/list")
    public Response<List<Request>> getRequestList(@Valid @RequestBody PaginationDTO paginationDTO) {
        return Response.success(requestService.getRequestByPagination(paginationDTO));
    }

    @GetMapping("/{id}")
    public Response<Request> getRequestById(@PathVariable(name = "id") long id) {
        Request request = requestService.getRequestById(id);
        if (request == null) {
            return Response.error("Request not found");
        }

        return Response.success(request);
    }

    @PostMapping("/")
    public Response<Void> addRequest(@Valid @RequestBody RequestDTO requestDTO, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        requestService.addRequest(requestDTO, userId);
        return Response.success("Request added");
    }

    @PutMapping("/{id}")
    public Response<Void> updateRequest(@PathVariable(name = "id") long id, @RequestBody RequestDTO requestDTO,
                                        HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        Request requests = requestService.getRequestById(id);
        if (requests == null) {
            return Response.error("Request not found");
        }

        requestService.updateRequest(requests, requestDTO, userId);
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

    @PutMapping("/{id}/handle")
    public Response<Void> handleRequest(@PathVariable(name = "id") long id, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        Request requests = requestService.getRequestById(id);
        if (requests == null) {
            return Response.error("Request not found");
        }

        requestService.handleRequest(requests, userId);
        return Response.success("Request handled");
    }

}
