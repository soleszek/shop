package com.sylwesteroleszek.cart;

import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.products.Product;

public class ProductInCart {
    Long productId;
    int quantity;
    Boolean isBought;

    public ProductInCart() {
    }

    public ProductInCart(Long productId, int quantity, Boolean isBought) {
        this.productId = productId;
        this.quantity = quantity;
        this.isBought = isBought;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getBought() {
        return isBought;
    }

    public void setBought(Boolean bought) {
        isBought = bought;
    }
}
