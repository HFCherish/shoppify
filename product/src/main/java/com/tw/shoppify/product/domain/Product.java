package com.tw.shoppify.product.domain;

import java.io.Serializable;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class Product implements Serializable {
    private String name;
    private String store;

    public String getName() {
        return name;
    }

    public String getStore() {
        return store;
    }
}
