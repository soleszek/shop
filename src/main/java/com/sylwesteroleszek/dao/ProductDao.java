package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.entity.Product;

import java.util.List;

public interface ProductDao {
    void updateProduct(Product product);
    Product findBy(String productId);
    List<Product> findAll();
}
