package com.sakurapuare.flightmanagement.common;

import com.sakurapuare.flightmanagement.constant.ResponseCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    private int code = ResponseCode.SUCCESS;
    private String message = "";
    private T data = null;
    private int time;

    public Response() {
        this.time = (int) (System.currentTimeMillis() / 1000);
    }

    public static Response<String> success(String message) {
        Response<String> response = new Response<>();
        response.setCode(ResponseCode.SUCCESS);
        response.setMessage(message);
        response.setData("");
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.SUCCESS);
        response.setData(data);
        response.setMessage("Success");
        return response;
    }

    public static <T> Response<T> success(String message, T data) {
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.SUCCESS);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> error(String message) {
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.BAD_REQUEST);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> error(int code, String message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> error(int code) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage("Error");
        return response;
    }

    public static <T> Response<T> error(int code, String message, T data) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
