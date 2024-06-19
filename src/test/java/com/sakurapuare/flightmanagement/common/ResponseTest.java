package com.sakurapuare.flightmanagement.common;

import com.sakurapuare.flightmanagement.constant.ResponseCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResponseTest {

    @Test
    void testSuccessWithMessage() {
        Response<String> response = Response.success("Success message");
        assertEquals(ResponseCode.SUCCESS, response.getCode());
        assertEquals("Success message", response.getMessage());
        assertEquals(response.getData(), "");
    }

    @Test
    void testSuccessWithData() {
        String data = "Test data";
        Response<String> response = Response.success(data);
        assertEquals(ResponseCode.SUCCESS, response.getCode());
        assertEquals(data, response.getMessage());
        assertEquals(response.getData(), "");
    }

    @Test
    void testSuccessWithMessageAndData() {
        String data = "Test data";
        Response<String> response = Response.success("Success message", data);
        assertEquals(ResponseCode.SUCCESS, response.getCode());
        assertEquals("Success message", response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    void testErrorWithMessage() {
        Response<String> response = Response.error("Error message");
        assertEquals(ResponseCode.BAD_REQUEST, response.getCode());
        assertEquals("Error message", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    void testErrorWithCodeAndMessage() {
        Response<String> response = Response.error(500, "Internal Server Error");
        assertEquals(500, response.getCode());
        assertEquals("Internal Server Error", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    void testErrorWithCode() {
        Response<String> response = Response.error(404);
        assertEquals(404, response.getCode());
        assertEquals("Error", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    void testErrorWithCodeMessageAndData() {
        String data = "Error data";
        Response<String> response = Response.error(400, "Bad Request", data);
        assertEquals(400, response.getCode());
        assertEquals("Bad Request", response.getMessage());
        assertEquals(data, response.getData());
    }
}