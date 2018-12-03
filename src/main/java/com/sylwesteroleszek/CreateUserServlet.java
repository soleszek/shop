package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.sylwesteroleszek.entity.NewUser;

import javax.servlet.ServletContext;
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

        JsonClass jsonClass = new JsonClass();

        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //String file = "/WEB-INF/data.json";
        String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
        //ServletContext context = getServletContext();
        //InputStream is = context.getResourceAsStream(file);
        InputStream is = new FileInputStream(file);

        if(is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            jsonClass = gson.fromJson(reader, JsonClass.class);
        }

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

        String path = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
        //String realPath = getServletContext().getRealPath(file);

        try {
            FileWriter writer = new FileWriter(path);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("index.jsp");
        //resp.getWriter().append("Użytkownik " + newUser.getUsername() + " utworzony");
        //resp.getWriter().append(jsonClass.getNewUsers().toString());
        //resp.getWriter().append(newUser.toString());
    }
}
