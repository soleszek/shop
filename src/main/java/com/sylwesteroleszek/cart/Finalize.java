package com.sylwesteroleszek.cart;

import com.sylwesteroleszek.dao.ActiveCartsDao;
import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.providers.DaoProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Finalize")
public class Finalize extends HttpServlet {

    NewUserDao newUserDao = DaoProvider.getInstance().getNewUserDao();
    ActiveCartsDao activeCartsDao = DaoProvider.getInstance().getActiveCartsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");

        //Cart

        double totalCashSpent = 0.0;

        List<ActiveCarts> productCartList;

        productCartList = activeCartsDao.findAll();

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

        activeCartsDao.saveOrUpdate(productCartList);

        //Stock

        List<NewUser> users = newUserDao.readUsers();
        NewUser newUser = new NewUser();

        for(NewUser nu : users){
            if(nu.getUsername().equals(user)){
                newUser = nu;
                newUser.setTotalCashSpend(nu.getTotalCashSpend()+(long)totalCashSpent);
            }
        }

        newUserDao.updateUser(newUser);

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
