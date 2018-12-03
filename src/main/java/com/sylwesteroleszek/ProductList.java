package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.sylwesteroleszek.products.Product;

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


        Gson gson = new Gson();
        JsonClass jsonClass = new JsonClass();

        //String file = "/WEB-INF/data.json";
        String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
        InputStream is = new FileInputStream(file);

        if(is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            jsonClass = gson.fromJson(reader, JsonClass.class);
        }

        List<Product> products = jsonClass.getProducts();

        req.getSession().setAttribute("productlist", products);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("productlist.jsp");
        requestDispatcher.forward(req, resp);
    }
}
