package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.sylwesteroleszek.entity.NewUser;
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

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonClass jsonClass;

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        jsonClass = JsonDaoImpl.readUsers();

        List<NewUser> newUsers = jsonClass.getUsers();

        NewUser newUser = new NewUser();
        newUser.setId(newUsers.size()+1l);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole("user");
        newUser.setTotalCashSpend(0l);

        newUsers.add(newUser);

        String json = gson.toJson(jsonClass);

        JsonDaoImpl.saveUser(json);

        resp.sendRedirect("index.jsp");
    }
}
