package com.sylwesteroleszek.daoImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.cart.ActiveCarts;
import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.entity.NewUser;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewUserDaoImplJson implements NewUserDao {

    static Gson gson = new Gson();
    static JsonClass jsonClass = new JsonClass();
    static Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();


    static String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";

    @Override
    public List<NewUser> readUsers() {

        return readJsonFile().getUsers();

    }

    @Override
    public void saveUser(NewUser newUser) {

        jsonClass = readJsonFile();

        List<NewUser> newUsers = jsonClass.getUsers();
        newUsers.add(newUser);

        String json = gson.toJson(jsonClass);

        saveJsonFile(json);

    }

    private JsonClass readJsonFile(){

        InputStream is = null;

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            return gson.fromJson(reader, JsonClass.class);
        }

        return null;
    }

    private void saveJsonFile(String json){
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
