package com.tw.shoppify.pricing.appservice.gateway;

import com.tw.shoppify.pricing.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Service
public class ProductGate {
    @Autowired
    RestTemplate restTemplate;

    public Optional<Product> findById(String productId) throws Exception {

        ResponseEntity<Product> getProduct = restTemplate.getForEntity("http://localhost:8001/products/" + productId, Product.class);
        HttpStatus statusCode = getProduct.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            return Optional.of(getProduct.getBody());
        }

        if (statusCode.is5xxServerError()) {
            throw new Exception("products service down");
        }

        return Optional.empty();
    }
}
