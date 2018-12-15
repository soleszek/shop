package com.sylwesteroleszek.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInCart {
    @SerializedName("productId")
    @Expose
    private Double productId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("quantity")
    @Expose
    private Double quantity;



    public ProductInCart() {
    }

    public ProductInCart(Double productId, String name, Double price, Double quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Double getProductId() {
        return productId;
    }

    public void setProductId(Double productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
