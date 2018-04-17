package com.tw.shoppify.product.domain;

import com.tw.stringutils.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
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
