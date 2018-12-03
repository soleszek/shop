package com.sylwesteroleszek;

import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.products.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonClass {
    List<NewUser> users;
    List<Product> products;
    HashMap<NewUser, List<Product>> shoppingCart;

    public JsonClass() {
    }

    public JsonClass(List<NewUser> users, List<Product> products) {
        this.users = users;
        this.products = products;
    }

    public List<NewUser> getUsers() {
        return users;
    }

    public void setUsers(List<NewUser> users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "JsonClass{" +
                "users=" + users +
                ", products=" + products +
                '}';
    }
}
