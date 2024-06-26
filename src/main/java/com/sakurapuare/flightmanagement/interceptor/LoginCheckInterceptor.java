package com.sakurapuare.flightmanagement.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @SuppressWarnings("null")
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");
        Response<String> res = Response.error(401, "Unauthorized");
        if (token == null) {
            log.info("token is null {}", request.getRequestURI());
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write(JSONObject.toJSONString(res));
            return false;
        }

        try {
            Claims claims = JwtTokenUtil.parseToken(token);
            request.setAttribute("userId", claims.get("userId"));
        } catch (Exception e) {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write(JSONObject.toJSONString(res));
            return false;
        }

        return true;
    }

}
