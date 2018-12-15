package com.sylwesteroleszek.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.entity.ActiveCarts;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    static Gson gson = new Gson();
    static Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();


    public static JsonClass readJsonFromFile(String path){

        InputStream is = null;

        try {
            is = new FileInputStream(path);
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

    public static List<ActiveCarts> readJsonFromFileActiveCarts(String path){
        InputStream is = null;

        try {
            is = new FileInputStream(path);
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

    public static void saveJsonFile(String path, String json){
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
