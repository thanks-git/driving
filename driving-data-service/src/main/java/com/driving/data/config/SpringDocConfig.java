package com.driving.data.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "driving-data-service", description = "华夏代驾数据服务(大数据)子系统", version = "1.0.0"))
public class SpringDocConfig {
    // todo: open-api configuration
}