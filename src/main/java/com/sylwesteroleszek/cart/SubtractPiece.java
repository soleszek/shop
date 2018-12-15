package com.sylwesteroleszek.cart;

import com.sylwesteroleszek.dao.ActiveCartsDao;
import com.sylwesteroleszek.dao.ProductDao;
import com.sylwesteroleszek.entity.ActiveCarts;
import com.sylwesteroleszek.entity.ProductInCart;
import com.sylwesteroleszek.entity.Product;
import com.sylwesteroleszek.providers.DaoProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SubtractPiece")
public class SubtractPiece extends HttpServlet {

    ProductDao productDao = DaoProvider.getInstance().getProduct();
    ActiveCartsDao activeCartsDao = DaoProvider.getInstance().getActiveCartsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");
        String productId = req.getParameter("subtract");

        Product product = productDao.findBy(productId);

        int quantity = product.getQuantity();
        product.setQuantity(quantity + 1);

        productDao.updateProduct(product);

        List<Product> products = productDao.findAll();

        //Cart

        List<ActiveCarts> productCartList = activeCartsDao.findAll();

        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                for (ProductInCart p : ac.getProductInCarts()){
                    if(p.getProductId() == (Double.parseDouble(productId))){
                        p.setQuantity(p.getQuantity() - 1);
                    }
                }
            }
        }

        activeCartsDao.saveOrUpdate(productCartList);

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
