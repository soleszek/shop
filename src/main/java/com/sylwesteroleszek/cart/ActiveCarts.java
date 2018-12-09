package com.sylwesteroleszek.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sylwesteroleszek.products.Product;

import java.util.List;
import java.util.Map;

public class ActiveCarts {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("products")
    @Expose
    private List<ProductInCart> productInCarts = null;

    public ActiveCarts() {
    }

    public ActiveCarts(String username, List<ProductInCart> productInCarts) {
        this.username = username;
        this.productInCarts = productInCarts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ProductInCart> getProductInCarts() {
        return productInCarts;
    }

    public void setProductInCarts(List<ProductInCart> productInCarts) {
        this.productInCarts = productInCarts;
    }

    @Override
    public String toString() {
        return "ActiveCarts{" +
                "username='" + username + '\'' +
                ", productInCarts=" + productInCarts +
                '}';
    }
}
