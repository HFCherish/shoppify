package com.tw.shoppify.product.api;

import com.tw.shoppify.product.domain.Product;
import org.springframework.stereotype.Component;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Component
public class Routes {

    public String productUrl(Product product) {
        return "/stores/" + product.getStore() + "/products/" + product.getId();
    }
}
