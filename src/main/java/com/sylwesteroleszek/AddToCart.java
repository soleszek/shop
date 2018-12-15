package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.cart.ActiveCarts;
import com.sylwesteroleszek.cart.ProductInCart;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    Gson gson = new Gson();

    String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
    String carts = "/home/sylwester/Dokumenty/projekty/sklep/carts.json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");
        String productId = (String)req.getParameter("productId");

        JsonClass jsonClass;

        jsonClass = JsonUtils.readProducts();

        List<Product> products = jsonClass.getProducts();

        int index = Integer.parseInt(productId) -1;

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

        List<ActiveCarts> productCartList = new ArrayList<>();

        Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();

        InputStream isC = new FileInputStream(carts);

        if(isC != null) {
            InputStreamReader isr = new InputStreamReader(isC);
            BufferedReader reader = new BufferedReader(isr);
            productCartList = gson.fromJson(reader, type);
        }

        Boolean isClientExist = false;
        Boolean isProductExist = false;

        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                isClientExist = true;
                for (ProductInCart p : ac.getProductInCarts()){
                    if(p.getProductId() == (Double.parseDouble(productId))){
                        isProductExist = true;
                        p.setQuantity(p.getQuantity() + 1);
                    }
                }

                if(isProductExist == false) {
                    ProductInCart productInCart = new ProductInCart();
                    productInCart.setProductId(Double.parseDouble(productId));
                    productInCart.setName(products.get(index).getName());
                    productInCart.setPrice((double)products.get(index).getPrice());
                    productInCart.setQuantity(1.0);
                    List<ProductInCart> productsInCart = ac.getProductInCarts();
                    productsInCart.add(productInCart);
                    ac.setProductInCarts(productsInCart);
                }
            }
        }

        if(isClientExist == false) {
            ActiveCarts activeCarts = new ActiveCarts();
            activeCarts.setUsername(user);
            ProductInCart productInCart = new ProductInCart();
            productInCart.setProductId(Double.parseDouble(productId));
            productInCart.setName(products.get(index).getName());
            productInCart.setPrice((double)products.get(index).getPrice());
            productInCart.setQuantity(1.0);
            List<ProductInCart> productsInCart = new ArrayList<>();
            productsInCart.add(productInCart);
            activeCarts.setProductInCarts(productsInCart);
            productCartList.add(activeCarts);
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

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("productlist.jsp");
        requestDispatcher.forward(req, resp);
    }
}
