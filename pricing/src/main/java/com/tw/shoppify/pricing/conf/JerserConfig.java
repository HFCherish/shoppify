package com.tw.shoppify.pricing.conf;

import com.tw.shoppify.pricing.api.CurrentPricingApi;
import com.tw.shoppify.pricing.api.EchoApi;
import com.tw.shoppify.pricing.api.PricingApi;
import com.tw.shoppify.pricing.api.ProductApi;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Component
public class JerserConfig extends ResourceConfig {
    public JerserConfig() {
//        packages("com.tw.shoppify.pricing");
        registerClasses(
                CurrentPricingApi.class,
                EchoApi.class,
                ProductApi.class,
                PricingApi.class
        );
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
