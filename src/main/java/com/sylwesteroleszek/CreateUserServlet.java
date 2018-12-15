package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.providers.DaoProvider;
import com.sylwesteroleszek.utils.JsonDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {

    NewUserDao newUserDao = DaoProvider.getInstance().getNewUserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        List<NewUser> newUsers = newUserDao.readUsers();

        NewUser newUser = new NewUser();
        newUser.setId(newUsers.size()+1l);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole("user");
        newUser.setTotalCashSpend(0l);

        newUserDao.saveUser(newUser);

        resp.sendRedirect("index.jsp");
    }
}
