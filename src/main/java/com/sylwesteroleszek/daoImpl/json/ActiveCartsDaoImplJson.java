package com.sylwesteroleszek.daoImpl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.entity.ActiveCarts;
import com.sylwesteroleszek.dao.ActiveCartsDao;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActiveCartsDaoImplJson implements ActiveCartsDao {

    Gson gson = new Gson();
    JsonClass jsonClass = new JsonClass();
    Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();

    static String carts = "/home/sylwester/Dokumenty/projekty/sklep/carts.json";


    @Override
    public void saveOrUpdate(List<ActiveCarts> activeCarts) {

        //List<ActiveCarts> activeCartsList = readJsonFile();

        String json = gson.toJson(activeCarts, type);

        saveJsonFile(json);
    }

    @Override
    public List<ActiveCarts> findAll() {
        return readJsonFile();
    }

    private List<ActiveCarts> readJsonFile(){
        InputStream is = null;

        try {
            is = new FileInputStream(carts);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            return gson.fromJson(reader, type);
        }

        return null;
    }

    private void saveJsonFile(String json){
        try {
            FileWriter writer = new FileWriter(carts);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
