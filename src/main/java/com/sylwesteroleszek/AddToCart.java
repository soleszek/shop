package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.sylwesteroleszek.products.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonClass jsonClass = new JsonClass();

        String file = "/WEB-INF/data.json";
        ServletContext context = getServletContext();
        InputStream is = context.getResourceAsStream(file);

        if(is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            jsonClass = gson.fromJson(reader, JsonClass.class);
        }

        //int productId = Integer.getInteger(req.getSession().getAttribute("productId"));
        final String productId = (String)req.getParameter("productId");
        //int productId = Integer.getInteger((req.getParameter("productId")));

        List<Product> products = jsonClass.getProducts();

      // products.stream().filter(e -> String.valueOf(e.getId()).equals(productId)).findFirst().orElse(null);

        int index = Integer.parseInt(productId) -1;
        Product productToChange = products.get(index);

        int quantity = productToChange.getQuantity() - 1;
        productToChange.setQuantity(quantity);

        Product yourProduct = new Product(1l, productToChange.getName(), productToChange.getDescription(), productToChange.getPrice(), 1);

        List<Product> productsInShoppingCart = new ArrayList<>();
        productsInShoppingCart.add(yourProduct);


        req.getSession().setAttribute("productlist", products);
        req.getSession().setAttribute("productsInShoppingCart", productsInShoppingCart);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("productlist.jsp");
        requestDispatcher.forward(req, resp);

        //resp.getWriter().println(products.toString());
    }
}
