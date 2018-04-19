package com.tw.shoppify.inventory.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hf_cherish
 * @date 4/19/18
 */
public interface OutboundOrderRepo extends JpaRepository<OutboundOrder, String> {
}
