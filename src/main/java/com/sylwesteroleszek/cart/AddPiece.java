package com.sylwesteroleszek.cart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.dao.ProductDao;
import com.sylwesteroleszek.products.Product;
import com.sylwesteroleszek.providers.DaoProvider;
import com.sylwesteroleszek.utils.JsonDaoImpl;

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
    ProductDao productDao = DaoProvider.getInstance().getProduct();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");
        String productId = req.getParameter("add");

        Product product = productDao.findBy(productId);

        int quantity = product.getQuantity();
        product.setQuantity(quantity - 1);

        productDao.updateProduct(product);

        List<Product> products = productDao.findAll();

        //Cart

        Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();

        List<ActiveCarts> productCartList = JsonDaoImpl.readCarts();

        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                for (ProductInCart p : ac.getProductInCarts()){
                    if(p.getProductId() == (Double.parseDouble(productId))){
                        p.setQuantity(p.getQuantity() + 1);
                    }
                }
            }
        }

        String jsonCarts = gson.toJson(productCartList, type);

        JsonDaoImpl.saveProductToCart(jsonCarts);

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
