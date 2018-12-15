package com.sylwesteroleszek.daoImpl.json;

import com.google.gson.Gson;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.dao.ProductDao;
import com.sylwesteroleszek.entity.Product;
import com.sylwesteroleszek.utils.JsonUtils;
import java.util.List;

public class ProductDaoJson implements ProductDao {

    Gson gson = new Gson();
    JsonClass jsonClass = new JsonClass();

    String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";

    public void updateProduct(Product product) {

        Long indexLong = (product.getId() - 1);
        int index = indexLong.intValue();

        jsonClass = JsonUtils.readJsonFromFile(file);

        List<Product> products = jsonClass.getProducts();

        products.set(index, product);

        String json = gson.toJson(jsonClass);

        JsonUtils.saveJsonFile(file, json);
    }

    public Product findBy(String productId){

        Double productIdD = Double.parseDouble(productId);

        int productIdInt = productIdD.intValue();

        return  JsonUtils.readJsonFromFile(file).getProducts().get(productIdInt - 1);
    }

    @Override
    public List<Product> findAll() {
        return JsonUtils.readJsonFromFile(file).getProducts();
    }
}
