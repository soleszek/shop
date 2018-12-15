package com.sylwesteroleszek;

import com.sylwesteroleszek.dao.ProductDao;
import com.sylwesteroleszek.entity.Product;
import com.sylwesteroleszek.providers.DaoProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/ProductList")
public class ProductList extends HttpServlet {

    ProductDao productDao = DaoProvider.getInstance().getProduct();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = productDao.findAll();

        req.getSession().setAttribute("productlist", products);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("productlist.jsp");
        requestDispatcher.forward(req, resp);
    }
}
