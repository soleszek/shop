package com.sylwesteroleszek.providers;

import com.sylwesteroleszek.dao.ActiveCartsDao;
import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.dao.ProductDao;
import com.sylwesteroleszek.daoImpl.ActiveCartsDaoImplJson;
import com.sylwesteroleszek.daoImpl.NewUserDaoImplHibernate;
import com.sylwesteroleszek.daoImpl.ProductDaoJson;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private NewUserDao newUserDao;
    private ProductDao productInCartDao;
    private ActiveCartsDao activeCartsDao;

    private DaoProvider(){
        newUserDao = new NewUserDaoImplHibernate();
        productInCartDao = new ProductDaoJson();
        activeCartsDao = new ActiveCartsDaoImplJson();
    }

    public static DaoProvider getInstance(){
        return instance;
    }

    public NewUserDao getNewUserDao(){
        return newUserDao;
    }

    public ProductDao getProductInCartDao() {
        return productInCartDao;
    }

    public ActiveCartsDao getActiveCartsDao() {
        return activeCartsDao;
    }
}
