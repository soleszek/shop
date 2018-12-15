package com.sylwesteroleszek.cart;

import com.sylwesteroleszek.dao.ActiveCartsDao;
import com.sylwesteroleszek.dao.ProductDao;
import com.sylwesteroleszek.products.Product;
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

@WebServlet("/RemoveProduct")
public class RemoveProduct extends HttpServlet {

    ProductDao productDao = DaoProvider.getInstance().getProduct();
    ActiveCartsDao activeCartsDao = DaoProvider.getInstance().getActiveCartsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");
        String productId = req.getParameter("remove");

        //Cart

        double numberOfPieces = 0.0;

        List<ActiveCarts> productCartList;

        productCartList = activeCartsDao.findAll();

        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                List<ProductInCart> productsInCart = ac.getProductInCarts();

                for( int i = 0; i < productsInCart.size(); i++ )
                {

                    if(productsInCart.get(i).getProductId() == Double.parseDouble(productId)){
                        numberOfPieces = productsInCart.get(i).getQuantity();
                        productsInCart.remove(productsInCart.get(i));
                    }
                }
            }
        }

        activeCartsDao.saveOrUpdate(productCartList);

        //Stock

        Product product = productDao.findBy(productId);
        int quantity = product.getQuantity();
        product.setQuantity(quantity + (int)numberOfPieces);

        productDao.updateProduct(product);

        List<ProductInCart> actualProductsInCart = new ArrayList<>();
        for(ActiveCarts ac : productCartList){
            if(ac.getUsername().equals(user)){
                actualProductsInCart = ac.getProductInCarts();
            }
        }

        List<Product> products = productDao.findAll();

        req.getSession().setAttribute("productlist", products);
        req.getSession().setAttribute("productsInCart", actualProductsInCart);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ShoppingCart.jsp");
        requestDispatcher.forward(req, resp);
    }
}
