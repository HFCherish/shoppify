package com.tw.shoppify.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@SpringBootApplication
@EnableEurekaClient
public class PricingApp {
    public static void main(String[] args) {
        SpringApplication.run(PricingApp.class, args);
    }
}
