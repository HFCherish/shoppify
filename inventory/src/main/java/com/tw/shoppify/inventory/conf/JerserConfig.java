package com.tw.shoppify.inventory.conf;

import com.tw.shoppify.inventory.api.EchoApi;
import com.tw.shoppify.inventory.api.InventoryApi;
import com.tw.shoppify.inventory.api.OutboundOrderApi;
import com.tw.shoppify.inventory.api.ProductApi;
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
//        packages("com.tw.shoppify.inventory");
        registerClasses(
                EchoApi.class,
                ProductApi.class,
                InventoryApi.class,
                OutboundOrderApi.class
        );
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
