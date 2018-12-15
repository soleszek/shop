package com.sylwesteroleszek.daoImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.cart.ActiveCarts;
import com.sylwesteroleszek.cart.ProductInCart;
import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.dao.ProductDao;
import com.sylwesteroleszek.products.Product;
import com.sylwesteroleszek.providers.DaoProvider;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJson implements ProductDao {

    Gson gson = new Gson();
    JsonClass jsonClass = new JsonClass();
    Type type = new TypeToken<ArrayList<ActiveCarts>>() {
    }.getType();


    String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";

    public void updateProduct(Product product) {

        Long indexLong = (product.getId() - 1);
        int index = indexLong.intValue();

        jsonClass = readJsonFile();

        List<Product> products = jsonClass.getProducts();

        products.set(index, product);

        String json = gson.toJson(jsonClass);

        saveJsonFile(json);
    }

    public Product findBy(String productId){

        Double productIdD = Double.parseDouble(productId);

        int productIdInt = productIdD.intValue();

        return readJsonFile().getProducts().get(productIdInt - 1);
    }

    @Override
    public List<Product> findAll() {
        return readJsonFile().getProducts();
    }



    private JsonClass readJsonFile(){

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
