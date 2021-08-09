package com.company.controller;

import com.company.model.User;
import com.company.service.UserService;
import com.company.service.impl.UserServiceImpl;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class UserController {

    UserService service = new UserServiceImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@QueryParam("email") String email, @QueryParam("password") String password) {
        System.out.println(email);
        System.out.println(password);
        JSONObject user = service.fetchUsers(email, password);
        if (user != null)
            return Response.ok(user.toString()).status(Response.Status.OK).build();
        else
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        service.addUser(user);
        return Response.ok(user).status(Response.Status.OK).build();
    }

    @PUT
    @Path("u/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser(@PathParam("id") String id, User u1) {
        return u1;
    }

    @DELETE
    @Path("u/{id}")
    public void deleteUser(@PathParam("id") String id) {
        service.deleteUser(id);

    }
}
