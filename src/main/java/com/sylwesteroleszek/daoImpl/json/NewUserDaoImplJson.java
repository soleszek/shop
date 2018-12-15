package com.sylwesteroleszek.daoImpl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.entity.ActiveCarts;
import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.entity.NewUser;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewUserDaoImplJson implements NewUserDao {

    Gson gson = new Gson();
    JsonClass jsonClass = new JsonClass();
    Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();


    String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";

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

    public void updateUser(NewUser newUser){

        Long indexLong = (newUser.getId() - 1);
        int index = indexLong.intValue();

        jsonClass = readJsonFile();

        List<NewUser> newUsers = jsonClass.getUsers();
        newUsers.set(index, newUser);

        String json = gson.toJson(jsonClass);

        saveJsonFile(json);
    }

    private JsonClass readJsonFile() {

        InputStream is = null;

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
