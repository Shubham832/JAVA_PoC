package com.company.dao;

import com.company.model.User;
import org.json.JSONObject;

public interface UserDao {
    void addUser(User u1);

    JSONObject fetchUsers(String email, String password);

    void deleteUser(String userId);
}
