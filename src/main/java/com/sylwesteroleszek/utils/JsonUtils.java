package com.sylwesteroleszek.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.cart.ActiveCarts;
import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.products.Product;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    static Gson gson = new Gson();
    static JsonClass jsonClass = new JsonClass();
    static Type type = new TypeToken<ArrayList<ActiveCarts>>() {
    }.getType();


    static String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";
    static String carts = "/home/sylwester/Dokumenty/projekty/sklep/carts.json";

    public static JsonClass readUsers() throws FileNotFoundException {
        InputStream is = new FileInputStream(file);

        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            jsonClass = gson.fromJson(reader, JsonClass.class);
        }

        return jsonClass;
    }

    public static JsonClass readProducts() throws FileNotFoundException {
        InputStream isP = new FileInputStream(file);

        if (isP != null) {
            InputStreamReader isr = new InputStreamReader(isP);
            BufferedReader reader = new BufferedReader(isr);
            jsonClass = gson.fromJson(reader, JsonClass.class);
        }

        return jsonClass;
    }

    public static List<ActiveCarts> readCarts() throws FileNotFoundException {
        InputStream isC = new FileInputStream(carts);

        InputStreamReader isr = new InputStreamReader(isC);
        BufferedReader reader = new BufferedReader(isr);

        return gson.fromJson(reader, type);
    }

    public static void saveUser(String json) {

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveProduct(String json){
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveProductToCart(String json) {
        try {
            FileWriter writer = new FileWriter(carts);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
