package com.tw.shoppify.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
}
