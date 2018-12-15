package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.cart.ActiveCarts;

import java.util.List;

public interface ActiveCartsDao {
    ActiveCarts readUsers();
    void saveUser(ActiveCarts activeCarts);
}
