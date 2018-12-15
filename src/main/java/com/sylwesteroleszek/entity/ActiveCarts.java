package com.sylwesteroleszek.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
