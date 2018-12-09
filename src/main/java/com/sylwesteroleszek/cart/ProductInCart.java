package com.sylwesteroleszek.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.products.Product;

public class ProductInCart {
    @SerializedName("productId")
    @Expose
    private Double productId;

    @SerializedName("quantity")
    @Expose
    private Double quantity;

    public ProductInCart() {
    }

    public ProductInCart(Double productId, Double quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Double getProductId() {
        return productId;
    }

    public void setProductId(Double productId) {
        this.productId = productId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
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
