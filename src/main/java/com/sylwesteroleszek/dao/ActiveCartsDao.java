package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.entity.ActiveCarts;

import java.util.List;

public interface ActiveCartsDao {
    void saveOrUpdate(List<ActiveCarts> activeCarts);
    List<ActiveCarts> findAll();
}
