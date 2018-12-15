package com.sylwesteroleszek.daoImpl.json;

import com.google.gson.Gson;
import com.sylwesteroleszek.JsonClass;
import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.utils.JsonUtils;
import java.util.List;

public class NewUserDaoImplJson implements NewUserDao {

    Gson gson = new Gson();
    JsonClass jsonClass = new JsonClass();

    String file = "/home/sylwester/Dokumenty/projekty/sklep/data.json";

    @Override
    public List<NewUser> readUsers() {

        return JsonUtils.readJsonFromFile(file).getUsers();

    }

    @Override
    public void saveUser(NewUser newUser) {

        jsonClass = JsonUtils.readJsonFromFile(file);

        List<NewUser> newUsers = jsonClass.getUsers();
        newUsers.add(newUser);

        String json = gson.toJson(jsonClass);

        JsonUtils.saveJsonFile(file, json);

    }

    public void updateUser(NewUser newUser){

        Long indexLong = (newUser.getId() - 1);
        int index = indexLong.intValue();

        jsonClass = JsonUtils.readJsonFromFile(file);

        List<NewUser> newUsers = jsonClass.getUsers();
        newUsers.set(index, newUser);

        String json = gson.toJson(jsonClass);

        JsonUtils.saveJsonFile(file, json);
    }

}
