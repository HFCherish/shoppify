package com.tw.shoppify.inventory.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Repository
public interface InventoryRepo extends JpaRepository<Inventory, String> {
    Optional<Inventory> findFirstByProductIdOrderByCreateAtDesc(String productId);
//    List<Inventory> findByProductId(String productId);
//
//    @Query(value = "select * from pricings where id in ( select SUBSTRING_INDEX(GROUP_CONCAT(id order by create_at DESC), ',', 1) id from pricings group by product_id )",
//            nativeQuery = true)
//    List<Inventory> findLatestPricings();
//
}
