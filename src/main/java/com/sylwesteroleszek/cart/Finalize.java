package com.sylwesteroleszek.cart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.entity.NewUser;
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

@WebServlet("/Finalize")
public class Finalize extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");

        JsonClass jsonClass;

        //Cart

        double totalCashSpent = 0.0;

        List<ActiveCarts> productCartList;

        Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();

        productCartList = JsonDaoImpl.readCarts();

        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                for (ProductInCart p : ac.getProductInCarts()){
                    totalCashSpent = totalCashSpent + p.getQuantity()*p.getPrice();
                }
            }
        }

        for(int i = 0; i < productCartList.size(); i++){
            if(productCartList.get(i).getUsername().equals(user)){
                productCartList.remove(productCartList.get(i));
            }
        }

        String jsonCarts = gson.toJson(productCartList, type);

        JsonDaoImpl.saveProductToCart(jsonCarts);

        //Stock

        jsonClass = JsonDaoImpl.readProducts();

        List<NewUser> users = jsonClass.getUsers();

        for(NewUser nu : users){
            if(nu.getUsername().equals(user)){
                nu.setTotalCashSpend(nu.getTotalCashSpend()+(long)totalCashSpent);
            }
        }

        String jsonData = gson.toJson(jsonClass);

        JsonDaoImpl.saveProduct(jsonData);

        List<ProductInCart> actualProductsInCart = new ArrayList<>();
        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                actualProductsInCart = ac.getProductInCarts();
            }
        }

        req.getSession().setAttribute("productsInCart", actualProductsInCart);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ShoppingCart.jsp");
        requestDispatcher.forward(req, resp);
    }

}
