package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.cart.ActiveCarts;
import com.sylwesteroleszek.cart.ProductInCart;

import java.util.List;

public interface ActiveCartsDao {
    void saveOrUpdate(List<ActiveCarts> activeCarts);
    List<ActiveCarts> findAll();
}
