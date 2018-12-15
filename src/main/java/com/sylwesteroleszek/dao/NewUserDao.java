package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.entity.NewUser;

import java.io.FileNotFoundException;
import java.util.List;

public interface NewUserDao {
    List<NewUser> readUsers() throws FileNotFoundException;
    void saveUser(NewUser newUser) throws FileNotFoundException;
}
