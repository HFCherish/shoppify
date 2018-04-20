package com.tw.shoppify.pricing.appservice.gateway;

import com.mysql.jdbc.StringUtils;
import com.netflix.discovery.EurekaClient;
import com.tw.shoppify.pricing.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ProductGateWay() {
        this.restTemplate = new RestTemplate();
    }

    public Optional<Product> findById(String productId) {

        try {
            ResponseEntity<Product> getProduct = restTemplate.getForEntity(productUrl("/products/" + productId), Product.class);
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
