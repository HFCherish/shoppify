package com.tw.shoppify.product;

import com.tw.shoppify.product.api.EchoApi;
import com.tw.shoppify.product.api.ProductQueryApi;
import com.tw.shoppify.product.api.ProductsApi;
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
//        packages("com.tw.shoppify.product");
        register(EchoApi.class);
        register(ProductQueryApi.class);
        register(ProductsApi.class);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
