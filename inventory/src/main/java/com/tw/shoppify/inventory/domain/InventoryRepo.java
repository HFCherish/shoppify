package com.tw.shoppify.inventory.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Repository
public interface InventoryRepo extends JpaRepository<Inventory, String> {
//    List<Inventory> findByProductId(String productId);
//
//    @Query(value = "select * from pricings where id in ( select SUBSTRING_INDEX(GROUP_CONCAT(id order by create_at DESC), ',', 1) id from pricings group by product_id )",
//            nativeQuery = true)
//    List<Inventory> findLatestPricings();
//
//    List<Inventory> findFirstByProductIdOrderByCreateAtDesc(String productId);
}
