package com.company.dao.impl;

import com.company.dao.UserDao;
import com.company.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.List;

public class UserDaoImpl implements UserDao {

    List<User> users;

    public void addUser(User u1) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase userDB = mongoClient.getDatabase("userDB");
        MongoCollection<Document> userList = userDB.getCollection("users");
        Document doc = new Document();
        doc.append("fname", u1.getFname());
        doc.append("lname", u1.getLname());
        doc.append("phone", u1.getPhone());
        doc.append("password", u1.getPassword());
        doc.append("email", u1.getEmail());
        JSONObject jsonObject = new JSONObject(u1.getAddress());
        System.out.println(jsonObject);
        Document address = Document.parse(jsonObject.toString());
        doc.append("address", address);
        userList.insertOne(doc);
    }

    public JSONObject fetchUsers(String email, String password) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase userDB = mongoClient.getDatabase("userDB");
        MongoCollection<Document> userList = userDB.getCollection("users");
        Document user = userList.find(Filters.and(Filters.eq("email", email), Filters.eq("password", password))).first();
        assert user != null;
        return new JSONObject(user.toJson());
    }

    public void updateUser(String userId, String value) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase userDB = mongoClient.getDatabase("userDB");
        MongoCollection<Document> userList = userDB.getCollection("users");
        userList.updateOne(Filters.eq("_id", new ObjectId(userId)), Updates.set("firstName", value));
    }

    public void deleteUser(String userId) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase userDB = mongoClient.getDatabase("userDB");
        MongoCollection<Document> userList = userDB.getCollection("users");
        userList.deleteOne(Filters.eq("_id", new ObjectId(userId)));
        mongoClient.close();
    }
}
