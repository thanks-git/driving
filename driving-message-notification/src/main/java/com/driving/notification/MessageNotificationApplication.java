package com.driving.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
// @ComponentScan("com.driving.*")
@EnableAsync
public class MessageNotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageNotificationApplication.class, args);
    }
}
