package com.company.service;

import com.company.model.User;
import org.json.JSONObject;

public interface UserService {

    void addUser(User user);

    void deleteUser(String id);

    JSONObject fetchUsers(String email, String password);
}
