package com.tw.shoppify.pricing.appservice.gateway;

import com.tw.shoppify.pricing.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.InternalServerErrorException;
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
            ResponseEntity<Product> getProduct = restTemplate.getForEntity("http://localhost:8001/products/" + productId, Product.class);
            return Optional.of(getProduct.getBody());
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
}
