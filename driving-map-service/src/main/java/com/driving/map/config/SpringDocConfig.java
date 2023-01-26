package com.driving.map.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "driving-map-service", description = "华夏代驾地图服务子系统", version = "1.0.0"))
public class SpringDocConfig {
    // todo: open-api configuration
}