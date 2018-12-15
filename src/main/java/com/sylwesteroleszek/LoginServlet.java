package com.sylwesteroleszek;

import com.sylwesteroleszek.cart.ActiveCarts;
import com.sylwesteroleszek.cart.ProductInCart;
import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.dao.ProductDao;
import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.providers.DaoProvider;
import com.sylwesteroleszek.utils.JsonDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    NewUserDao newUserDao = DaoProvider.getInstance().getNewUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<NewUser> newUsers = newUserDao.readUsers();

        String user = req.getParameter("username");
        String password = req.getParameter("password");

        Cookie loginCookie = null;

        for(NewUser u : newUsers) {

            if (user != null && user.equals(u.getUsername()) && password != null && password.equals(u.getPassword())) {
                loginCookie = new Cookie(u.getUsername(), u.getUsername());
                loginCookie.setMaxAge(30 * 60);

                resp.addCookie(loginCookie);

                Long totalCashSpend = 0l;

                if(u.getUsername().equals(user)){
                        totalCashSpend = u.getTotalCashSpend();
                }

                List<ActiveCarts> productCartList;

                productCartList = JsonDaoImpl.readCarts();

                List<ProductInCart> actualProductsInCart = new ArrayList<>();
                for(ActiveCarts ac : productCartList){
                    if(ac.getUsername().equals(user)){
                        actualProductsInCart = ac.getProductInCarts();
                    }
                }

                req.getSession().setAttribute("productsInCart", actualProductsInCart);
                req.setAttribute("totalCashSpend", totalCashSpend);
                req.getSession().setAttribute("user", u.getUsername());
                RequestDispatcher rD = req.getRequestDispatcher("loginSuccess.jsp");
                rD.forward(req, resp);
            }
        }

        if (loginCookie == null) {
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            resp.getWriter()
                    .println("<font color=red>Incorrect or missing login details</font>");
            rd.include(req, resp);
        }
    }

}
