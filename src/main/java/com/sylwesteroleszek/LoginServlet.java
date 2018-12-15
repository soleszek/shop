package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.cart.ActiveCarts;
import com.sylwesteroleszek.cart.ProductInCart;
import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.utils.JsonUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.sylwesteroleszek.utils.JsonUtils.readUsers;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonClass jsonClass = new JsonClass();

        //String file = "/WEB-INF/data.json";
        String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
        String carts = "/home/sylwester/Dokumenty/projekty/sklep/carts.json";
        //ServletContext context = getServletContext();

        List<NewUser> newUsers = JsonUtils.readUsers();

        /*InputStream is = new FileInputStream(file);

        if(is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            jsonClass = gson.fromJson(reader, JsonClass.class);
        }

        List<NewUser> newUsers = jsonClass.getUsers();*/

        String user = req.getParameter("username");
        String password = req.getParameter("password");

        Cookie loginCookie = null;

        for(NewUser u : newUsers) {

            if (user != null && user.equals(u.getUsername()) && password != null && password.equals(u.getPassword())) {
                loginCookie = new Cookie(u.getUsername(), u.getUsername());
                loginCookie.setMaxAge(30 * 60);

                resp.addCookie(loginCookie);
                //req.setAttribute(u.getUsername(), user);
                //resp.sendRedirect("loginSuccess.jsp");

                Long totalCashSpend = 0l;

                if(u.getUsername().equals(user)){
                        totalCashSpend = u.getTotalCashSpend();
                }

                List<ActiveCarts> productCartList = new ArrayList<>();

                Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();

                InputStream isC = new FileInputStream(carts);

                if(isC != null) {
                    InputStreamReader isr = new InputStreamReader(isC);
                    BufferedReader reader = new BufferedReader(isr);
                    productCartList = gson.fromJson(reader, type);
                }

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
