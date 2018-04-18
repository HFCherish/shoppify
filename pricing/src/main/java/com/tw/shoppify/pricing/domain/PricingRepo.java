package com.tw.shoppify.pricing.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Repository
public interface PricingRepo extends JpaRepository<Pricing, String> {
    List<Pricing> findByProductId(String productId);

    @Query(value = "select * from pricings where id in ( select SUBSTRING_INDEX(GROUP_CONCAT(id order by create_at DESC), ',', 1) id from pricings group by product_id )",
            nativeQuery = true)
    List<Pricing> findLatestPricings();
}
