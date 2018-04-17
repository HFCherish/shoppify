package com.tw.shoppify.pricing;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Component
public class JerserConfig extends ResourceConfig {
    public JerserConfig() {
        packages("com.tw.shoppify.pricing");
    }
}
