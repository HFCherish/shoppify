package com.tw.shoppify.inventory.appservice.gateway;

import com.alibaba.fastjson.JSONObject;
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

    public Optional<JSONObject> findById(String productId) {

        try {
            ResponseEntity<JSONObject> getProduct = restTemplate.getForEntity("http://localhost:8001/products/" + productId, JSONObject.class);
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
