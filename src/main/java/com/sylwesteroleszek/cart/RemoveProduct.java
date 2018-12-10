package com.sylwesteroleszek.cart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.products.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RemoveProduct")
public class RemoveProduct extends HttpServlet {
    Gson gson = new Gson();

    String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
    String carts = "/home/sylwester/Dokumenty/projekty/sklep/carts.json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");
        double productId = Double.parseDouble(req.getParameter("remove"));

        JsonClass jsonClass = new JsonClass();

        //Cart

        double numberOfPieces = 0.0;

        List<ActiveCarts> productCartList = new ArrayList<>();

        Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();

        InputStream isC = new FileInputStream(carts);

        if(isC != null) {
            InputStreamReader isr = new InputStreamReader(isC);
            BufferedReader reader = new BufferedReader(isr);
            productCartList = gson.fromJson(reader, type);
        }

        List<ProductInCart> productsInCartToRemove = new ArrayList<>();

        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                //productsInCart = ac.getProductInCarts();
                for(ProductInCart p : productsInCartToRemove){
                    if(p.getProductId() == productId){
                        numberOfPieces = p.getQuantity();
                        productsInCartToRemove.add(p);
                    }
                }
            }
        }



        String jsonCarts = gson.toJson(productCartList, type);

        try {
            FileWriter writer = new FileWriter(carts);
            writer.write(jsonCarts);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Stock

        InputStream isP = new FileInputStream(file);

        if(isP != null) {
            InputStreamReader isr = new InputStreamReader(isP);
            BufferedReader reader = new BufferedReader(isr);
            jsonClass = gson.fromJson(reader, JsonClass.class);
        }

        List<Product> products = jsonClass.getProducts();

        int index = ((int)(productId)) -1;

        products.get(index).setQuantity((int)numberOfPieces);

        String jsonData = gson.toJson(jsonClass);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(jsonData);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<ProductInCart> actualProductsInCart = new ArrayList<>();
        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                actualProductsInCart = ac.getProductInCarts();
            }
        }


        req.getSession().setAttribute("productlist", products);
        req.getSession().setAttribute("productsInCart", actualProductsInCart);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ShoppingCart.jsp");
        requestDispatcher.forward(req, resp);
    }
}
