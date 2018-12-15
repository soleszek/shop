package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.cart.ProductInCart;
import com.sylwesteroleszek.products.Product;

import java.util.List;

public interface ProductDao {
    void updateProduct(Product product);
    Product findBy(String productId);
    List<Product> findAll();
}
