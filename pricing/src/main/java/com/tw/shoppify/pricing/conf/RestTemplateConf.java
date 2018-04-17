package com.tw.shoppify.pricing.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Configuration
public class RestTemplateConf {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
