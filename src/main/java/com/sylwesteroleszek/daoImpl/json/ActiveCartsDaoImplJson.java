package com.sylwesteroleszek.daoImpl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sylwesteroleszek.entity.ActiveCarts;
import com.sylwesteroleszek.dao.ActiveCartsDao;
import com.sylwesteroleszek.utils.JsonUtils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActiveCartsDaoImplJson implements ActiveCartsDao {

    Gson gson = new Gson();
    Type type = new TypeToken<ArrayList<ActiveCarts>>(){}.getType();

    static String carts = "/home/sylwester/Dokumenty/projekty/sklep/carts.json";


    @Override
    public void saveOrUpdate(List<ActiveCarts> activeCarts) {

        String json = gson.toJson(activeCarts, type);

        JsonUtils.saveJsonFile(carts, json);
    }

    @Override
    public List<ActiveCarts> findAll() {
        return JsonUtils.readJsonFromFileActiveCarts(carts);
    }

}
