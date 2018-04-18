package com.tw.shoppify.inventory.appservice;

import com.tw.shoppify.inventory.appservice.gateway.ProductGateWay;
import com.tw.shoppify.inventory.domain.Inventory;
import com.tw.shoppify.inventory.domain.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Service
public class InventoryService {
    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    ProductGateWay productGateWay;

    public Inventory save(Product product, Inventory inventory) {
        return inventoryRepo.save(new Inventory(product.getId(), inventory.getAmount()));
    }

//    public List<Pricing> findAll(Product product) {
//        return inventoryRepo.findByProductId(product.getId());
//    }
//
//    public List<ProductCurrentPricing> findLatestPricings(String productId, Boolean getProductDetail) {
//        List<Pricing> pricings = StringUtils.isNullOrEmpty(productId) ? inventoryRepo.findLatestPricings() : inventoryRepo.findFirstByProductIdOrderByCreateAtDesc(productId);
//        return pricings.stream()
//                .map(p -> new ProductCurrentPricing(getProductDetail ? productGateWay.findById(p.getProductId()).get() : new Product(p.getProductId()), p))
//                .collect(Collectors.toList());
//    }
}
