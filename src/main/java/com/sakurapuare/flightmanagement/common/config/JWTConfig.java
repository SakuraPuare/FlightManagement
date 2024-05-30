package com.sakurapuare.flightmanagement.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {
    private String signKey;
    private long expire;
}
