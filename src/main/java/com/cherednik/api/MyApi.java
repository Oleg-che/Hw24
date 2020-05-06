package com.cherednik.api;

import com.cherednik.model.User;
import com.cherednik.util.UserUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class MyApi {
    private UserUtil userUtil = new UserUtil();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String json) {
        User user = UserUtil.fromJson(json);
        User saveUser = userUtil.addUser(user);
        if (saveUser.getId() != 0) {
            String resultJson = "{success}";
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{failed}";
            return Response.status(Response.Status.BAD_REQUEST).entity(resultJson).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(String json) {
        User user = UserUtil.fromJson(json);
        if (userUtil.updateUser(user) != 0) {
            String resultJson = UserUtil.toJson(user);
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{user not found}";
            return Response.status(Response.Status.NOT_FOUND).entity(resultJson).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        List<User> users = userUtil.getUser(id);
        if (users.size() != 0) {
            String result = UserUtil.toJson(users.get(0));
            return Response.status(Response.Status.OK).entity(result).build();
        } else {
            String result = "{user not found}";
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersList() {
        List<User> users = userUtil.getUsers();
        String resultJson = UserUtil.toJson(users);
        return Response.status(Response.Status.OK).entity(resultJson).build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(String json) {
        User user = UserUtil.fromJson(json);
        if (userUtil.deleteUser(user) != 0) {
            String resultJson = "{Success, user " + user.getName() + " was removed }";
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{Error, user not found}";
            return Response.status(Response.Status.NOT_FOUND).entity(resultJson).build();
        }
    }

    @DELETE
    @Path("/delete/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAllUsers() {
        int result = userUtil.deleteAllUsers();
        String resultJson = "{" + result + "}";
        return Response.status(Response.Status.OK).entity(resultJson).build();
    }
}
