package com.sylwesteroleszek.cart;

import java.util.List;
import java.util.Map;

public class HistoryOfClients {
    Map<String, List<ProductInCart>> products;

    public HistoryOfClients() {
    }

    public HistoryOfClients(Map<String, List<ProductInCart>> products) {
        this.products = products;
    }

    public Map<String, List<ProductInCart>> getProducts() {
        return products;
    }

    public void setProducts(Map<String, List<ProductInCart>> products) {
        this.products = products;
    }
}
