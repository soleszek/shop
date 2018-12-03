package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.sylwesteroleszek.entity.NewUser;

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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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

        List<NewUser> newUsers = jsonClass.getUsers();

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
