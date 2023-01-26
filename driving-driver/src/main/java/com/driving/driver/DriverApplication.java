package com.driving.driver;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ServletComponentScan
@ComponentScan("com.driving.*")
@EnableDistributedTransaction
public class DriverApplication {
    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }
}
