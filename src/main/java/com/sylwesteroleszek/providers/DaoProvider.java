package com.sylwesteroleszek.providers;

import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.dao.NewUserDaoImpl;
import com.sylwesteroleszek.entity.NewUser;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private NewUserDao newUserDao;

    private DaoProvider(){
        newUserDao = new NewUserDaoImpl();
    }

    public static DaoProvider getInstance(){
        return instance;
    }

    public NewUserDao getNewUserDao(){
        return newUserDao;
    }
}
