package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.sylwesteroleszek.products.Product;
import com.sylwesteroleszek.utils.JsonUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/ProductList")
public class ProductList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = JsonUtils.readProducts().getProducts();

        req.getSession().setAttribute("productlist", products);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("productlist.jsp");
        requestDispatcher.forward(req, resp);
    }
}
