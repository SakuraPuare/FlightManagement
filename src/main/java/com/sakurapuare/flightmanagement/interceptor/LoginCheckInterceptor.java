package com.sakurapuare.flightmanagement.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sakurapuare.flightmanagement.common.AuthInfo;
import com.sakurapuare.flightmanagement.common.Context;
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
        Response<Void> res = Response.error(401, "Unauthorized");
        if (token == null) {
            log.info("token is null");
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write(JSONObject.toJSONString(res));
            return false;
        }

        try {
            Claims claims = JwtTokenUtil.parseToken(token);
            request.setAttribute("userId", claims.get("userId"));

            AuthInfo authInfo = new AuthInfo();
            authInfo.setUserId(Long.parseLong(claims.get("userId").toString()));
            authInfo.setUsername(claims.get("username").toString());
            authInfo.setRole(Integer.parseInt(claims.get("role").toString()));
            Context.setCurrentInfo(authInfo);
        } catch (Exception e) {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write(JSONObject.toJSONString(res));
            return false;
        }

        return true;
    }

}
