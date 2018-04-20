package com.tw.shoppify.inventory.appservice.gateway;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import com.netflix.discovery.EurekaClient;
import com.tw.shoppify.inventory.appservice.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Service
public class ProductGateWay {
    RestTemplate restTemplate;

    public ProductGateWay() {
        this.restTemplate = new RestTemplate();
    }

    @Autowired
    EurekaClient discoveryClient;

    public String baseUrl() {
        String productService = System.getenv("PRODUCT_SERVICE_NAME");
        return StringUtils.isNullOrEmpty(productService) || discoveryClient == null ?
                "http://localhost:8001" :
                discoveryClient.getNextServerFromEureka(productService, false).getHomePageUrl();
    }

    public String productUrl(String path) {
        return baseUrl() + path;
    }

    public Optional<Product> findById(String productId) {

        try {
            ResponseEntity<JSONObject> getProduct = restTemplate.getForEntity(productUrl("/products/" + productId), JSONObject.class);
            return Optional.of(new Product(getProduct.getBody()));
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().is4xxClientError()) {
                return Optional.empty();
            }

            if (e.getStatusCode().is5xxServerError()) {
                throw new InternalServerErrorException("products service down");
            }
        }

        return Optional.empty();
    }

    public Product findByIdWithException(String productId) {
        return findById(productId).orElseThrow(() -> new NotFoundException("product not exists"));
    }
}
