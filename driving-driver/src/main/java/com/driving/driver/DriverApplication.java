package com.driving.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DriverApplication {
    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }
}
