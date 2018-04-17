package com.tw.shoppify.pricing.domain;

import com.tw.stringutils.IdGenerator;

import java.io.Serializable;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class Product implements Serializable {
    private String id;
    private String name;
    private String store;

    private Product() {
    }

    public Product(String name, String store) {
        this.name = name;
        this.store = store;
        this.id = IdGenerator.next();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStore() {
        return store;
    }
}
