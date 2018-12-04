package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.sylwesteroleszek.cart.HistoryOfClients;
import com.sylwesteroleszek.cart.ProductInCart;
import com.sylwesteroleszek.products.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");

        JsonClass jsonClass = new JsonClass();

        //String file = "/WEB-INF/data.json";
        String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
        String carts = "/home/sylwester/Dokumenty/projekty/sklep/carts.json";
        InputStream is = new FileInputStream(file);

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

        //Product yourProduct = new Product(1l, productToChange.getName(), productToChange.getDescription(), productToChange.getPrice(), 1);

        ProductInCart productInCart = new ProductInCart(productToChange.getId(), 1, false);


        String json1 = gson.toJson(jsonClass);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json1);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        HistoryOfClients historyOfClients = new HistoryOfClients();

        Map<String, List<ProductInCart>> shoppingCartOfAllClients = jsonClass.getShoppingCart();

        Boolean clientExist = false;

        for(String s : shoppingCartOfAllClients.keySet()) {
            if(s == user){
                List<ProductInCart> productsInShoppingCart = shoppingCartOfAllClients.get(user);
                clientExist = true;
            }
        }

        if(clientExist == false) {
            
        }

        productsInShoppingCart.add(productInCart);



        shoppingCartOfAllClients.put(user, productsInShoppingCart);

        String json2 = gson.toJson(shoppingCartOfAllClients);

        try {
            FileWriter writer = new FileWriter(carts);
            writer.write(json2);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        req.getSession().setAttribute("productlist", products);
        req.getSession().setAttribute("productsInShoppingCart", productsInShoppingCart);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("productlist.jsp");
        requestDispatcher.forward(req, resp);

        //resp.getWriter().println(products.toString());
    }
}
