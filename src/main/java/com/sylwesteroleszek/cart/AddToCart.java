package com.sylwesteroleszek.cart;

import com.sylwesteroleszek.entity.ActiveCarts;
import com.sylwesteroleszek.entity.ProductInCart;
import com.sylwesteroleszek.dao.ActiveCartsDao;
import com.sylwesteroleszek.dao.ProductDao;
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

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    ProductDao productDao = DaoProvider.getInstance().getProduct();
    ActiveCartsDao activeCartsDao = DaoProvider.getInstance().getActiveCartsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String)req.getSession().getAttribute("user");
        String productId = req.getParameter("productId");

        Product product = productDao.findBy(productId);

        int quantity = product.getQuantity();

        product.setQuantity(quantity - 1);

        productDao.updateProduct(product);

        //Cart

        List<ActiveCarts> productCartList;

        productCartList = activeCartsDao.findAll();

        Boolean isClientExist = false;
        Boolean isProductExist = false;
        int index = Integer.parseInt(productId) -1;
        List<Product> products = productDao.findAll();

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

        activeCartsDao.saveOrUpdate(productCartList);

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
