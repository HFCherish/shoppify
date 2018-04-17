package com.tw.shoppify.product.infrastructure.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.shoppify.product.domain.Product;
import org.springframework.data.domain.Example;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
// TODO: 4/17/18 move to infrastructure & add dto-for-db
public class ProductExampleFactory {
    public static Example<Product> productOfStoreExample(String store) {
        Product product = new ObjectMapper().convertValue(new JSONObject().fluentPut("store", store), Product.class);
        System.out.println(JSON.toJSONString(product));
        return Example.of(product);
    }
}
