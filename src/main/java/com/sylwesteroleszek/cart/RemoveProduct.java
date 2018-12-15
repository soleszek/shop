package com.sylwesteroleszek.cart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.products.Product;
import com.sylwesteroleszek.utils.JsonDaoImpl;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");
        double productId = Double.parseDouble(req.getParameter("remove"));

        JsonClass jsonClass = new JsonClass();

        //Cart

        double numberOfPieces = 0.0;

        List<ActiveCarts> productCartList;

        Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();

        productCartList = JsonDaoImpl.readCarts();

        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                List<ProductInCart> productsInCart = ac.getProductInCarts();

                for( int i = 0; i < productsInCart.size(); i++ )
                {

                    if(productsInCart.get(i).getProductId() == productId){
                        numberOfPieces = productsInCart.get(i).getQuantity();
                        productsInCart.remove(productsInCart.get(i));
                        //i--;
                    }
                }
            }
        }

        String jsonCarts = gson.toJson(productCartList, type);

        JsonDaoImpl.saveProductToCart(jsonCarts);

        //Stock

        jsonClass = JsonDaoImpl.readProducts();

        List<Product> products = jsonClass.getProducts();

        int index = ((int)(productId)) -1;

        products.get(index).setQuantity(products.get(index).getQuantity() + (int)numberOfPieces);

        String jsonData = gson.toJson(jsonClass);

        JsonDaoImpl.saveProduct(jsonData);

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
