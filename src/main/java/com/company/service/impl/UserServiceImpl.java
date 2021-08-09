package com.company.service.impl;

import com.company.dao.impl.UserDaoImpl;
import com.company.model.User;
import com.company.service.UserService;
import org.json.JSONObject;

public class UserServiceImpl implements UserService {

    UserDaoImpl dao = new UserDaoImpl();

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.deleteUser(id);
    }

    @Override
    public JSONObject fetchUsers(String email, String password) {
        return dao.fetchUsers(email, password);
    }
}
