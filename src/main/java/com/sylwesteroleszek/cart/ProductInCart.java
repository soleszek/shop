package com.sylwesteroleszek.cart;

import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.products.Product;

public class ProductInCart {
    Long productId;
    int quantity;

    public ProductInCart() {
    }

    public ProductInCart(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "ProductInCart{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
