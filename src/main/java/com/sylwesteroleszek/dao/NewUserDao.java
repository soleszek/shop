package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.entity.NewUser;

import java.util.List;

public interface NewUserDao {
    List<NewUser> readUsers();
    void saveUser(NewUser newUser);
}
