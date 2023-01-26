package com.driving.order.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "driving-order", description = "华夏代驾订单子系统", version = "1.0.0"))
public class SpringDocConfig {
    // todo: open-api configuration
}