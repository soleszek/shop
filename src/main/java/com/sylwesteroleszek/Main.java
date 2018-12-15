package com.sylwesteroleszek;

import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.providers.DaoProvider;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        NewUserDao newUserDao = DaoProvider.getInstance().getNewUserDao();

        NewUser newUser = new NewUser();
        List<NewUser> newUsers = newUserDao.readUsers();

        for(NewUser nu : newUsers){
            System.out.println(nu);
        }
    }
}
