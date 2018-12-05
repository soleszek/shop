package com.sylwesteroleszek.cart;

import java.util.List;
import java.util.Map;

public class ActiveCarts {
    Map<String, List<ProductInCart>> carts;

    public ActiveCarts() {
    }

    public ActiveCarts(Map<String, List<ProductInCart>> carts) {
        this.carts = carts;
    }

    public Map<String, List<ProductInCart>> getCarts() {
        return carts;
    }

    public void setCarts(Map<String, List<ProductInCart>> carts) {
        this.carts = carts;
    }
}
