package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.cart.ProductInCart;
import com.sylwesteroleszek.products.Product;

import java.util.List;

public interface ProductDao {
    List<Product> readProduct();
    void saveProduct(Product product);
}
