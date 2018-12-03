package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.entity.NewUser;

public interface NewUserDao {
    void saveOrUpdate(NewUser newUser);
    NewUser findBy(String login);
}
