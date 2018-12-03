package com.sylwesteroleszek.cart;

import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.products.Product;

public class Cart {
    NewUser client;
    Product product;
    Long productId;
    Boolean isBought;

    public Cart() {
    }

    public Cart(NewUser client, Product product, Long productId, Boolean isBought) {
        this.client = client;
        this.product = product;
        this.productId = productId;
        this.isBought = isBought;
    }

    public NewUser getClient() {
        return client;
    }

    public void setClient(NewUser client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Boolean getBought() {
        return isBought;
    }

    public void setBought(Boolean bought) {
        isBought = bought;
    }
}
