package com.sylwesteroleszek.cart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.products.Product;
import com.sylwesteroleszek.utils.JsonUtils;

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

@WebServlet("/AddPiece")
public class AddPiece extends HttpServlet {
    Gson gson = new Gson();

    String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
    String carts = "/home/sylwester/Dokumenty/projekty/sklep/carts.json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");
        double productId = Double.parseDouble(req.getParameter("add"));

        JsonClass jsonClass = JsonUtils.readProducts();

        List<Product> products = jsonClass.getProducts();

        int index = ((int)(productId)) -1;

        products.get(index).setQuantity(products.get(index).getQuantity() - 1);

        String jsonData = gson.toJson(jsonClass);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(jsonData);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Cart

        Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();

        List<ActiveCarts> productCartList = JsonUtils.readCarts();

        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                for (ProductInCart p : ac.getProductInCarts()){
                    if(p.getProductId() == (productId)){
                        p.setQuantity(p.getQuantity() + 1);
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
