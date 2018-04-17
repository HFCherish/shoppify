package com.tw.shoppify.product;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@SpringBootApplication
public class ProductApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new ProductApp()
                .configure(new SpringApplicationBuilder(ProductApp.class))
                .run(args);
    }
}
