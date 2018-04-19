package com.tw.shoppify.inventory.appservice.gateway;

import com.alibaba.fastjson.JSONObject;
import com.tw.shoppify.inventory.appservice.Product;
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

    public Optional<Product> findById(String productId) {

        try {
            ResponseEntity<JSONObject> getProduct = restTemplate.getForEntity("http://localhost:8001/products/" + productId, JSONObject.class);
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
