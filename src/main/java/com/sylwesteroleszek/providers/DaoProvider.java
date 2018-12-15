package com.sylwesteroleszek.providers;

import com.sylwesteroleszek.dao.ActiveCartsDao;
import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.dao.ProductDao;
import com.sylwesteroleszek.daoImpl.json.ActiveCartsDaoImplJson;
import com.sylwesteroleszek.daoImpl.json.NewUserDaoImplJson;
import com.sylwesteroleszek.daoImpl.json.ProductDaoJson;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private NewUserDao newUserDao;
    private ProductDao productDao;
    private ActiveCartsDao activeCartsDao;

    private DaoProvider(){
        newUserDao = new NewUserDaoImplJson();
        productDao = new ProductDaoJson();
        activeCartsDao = new ActiveCartsDaoImplJson();
    }

    public static DaoProvider getInstance(){
        return instance;
    }

    public NewUserDao getNewUserDao(){
        return newUserDao;
    }

    public ProductDao getProduct() {
        return productDao;
    }

    public ActiveCartsDao getActiveCartsDao() {
        return activeCartsDao;
    }
}
