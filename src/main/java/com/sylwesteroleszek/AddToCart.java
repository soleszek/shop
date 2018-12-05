package com.sylwesteroleszek;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.cart.ActiveCarts;
import com.sylwesteroleszek.cart.ProductInCart;
import com.sylwesteroleszek.products.Product;

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
import java.util.Map;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    Gson gson = new Gson();

    String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
    String carts = "/home/sylwester/Dokumenty/projekty/sklep/carts.json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");
        final String productId = (String)req.getParameter("productId");

        JsonClass jsonClass = new JsonClass();

        InputStream isP = new FileInputStream(file);

        if(isP != null) {
            InputStreamReader isr = new InputStreamReader(isP);
            BufferedReader reader = new BufferedReader(isr);
            jsonClass = gson.fromJson(reader, JsonClass.class);
        }

        List<Product> products = jsonClass.getProducts();

        int index = Integer.parseInt(productId) -1;
        //Product productToChange = products.get(index);

        //int quantity = productToChange.getQuantity() - 1;
        //productToChange.setQuantity(quantity);

        products.get(index).setQuantity(products.get(index).getQuantity() - 1);

        //Product yourProduct = new Product(1l, productToChange.getName(), productToChange.getDescription(), productToChange.getPrice(), 1);

        String jsonData = gson.toJson(jsonClass);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(jsonData);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Cart

        ProductInCart productInCart = new ProductInCart(Long.parseLong(productId), 1);

        ActiveCarts activeCarts = new ActiveCarts();

        InputStream isC = new FileInputStream(carts);

        Type type = new TypeToken<Map<String, ProductInCart>>(){}.getType();
        Map<String, List<ProductInCart>> shoppingCartOfAllClients = activeCarts.getCarts();

        if(isC != null) {
            InputStreamReader isr = new InputStreamReader(isC);
            BufferedReader reader = new BufferedReader(isr);
            //activeCarts = gson.fromJson(reader, type);
            shoppingCartOfAllClients = gson.fromJson(reader, Map.class);
        }

        //Map<String, List<ProductInCart>> shoppingCartOfAllClients = activeCarts.getCarts();



        Boolean clientExist = false;

        for(String s : shoppingCartOfAllClients.keySet()) {
            if(s.equals(user)){
                clientExist = true;
            }
        }

        if(clientExist == true) {
            List<ProductInCart> productsInShoppingCartOfGivenClient = shoppingCartOfAllClients.get(user);

            boolean isProductInCart = false;

            for(ProductInCart p : productsInShoppingCartOfGivenClient){
                if(p.getProductId() == Long.parseLong(productId)){
                    p.setQuantity(p.getQuantity() + 1);
                    isProductInCart = true;
                }
            }

            if(isProductInCart == true) {
                productsInShoppingCartOfGivenClient.add(productInCart);
            }

        } else {
            List<ProductInCart> productsInCart = new ArrayList<>();
            productsInCart.add(productInCart);
            shoppingCartOfAllClients.put(user, productsInCart);
        }

        String jsonCarts = gson.toJson(shoppingCartOfAllClients);

        try {
            FileWriter writer = new FileWriter(carts);
            writer.write(jsonCarts);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        req.getSession().setAttribute("productlist", products);
        //req.getSession().setAttribute("productsInShoppingCart", productsInShoppingCart);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("productlist.jsp");
        requestDispatcher.forward(req, resp);

        //resp.getWriter().println(products.toString());
    }
}
