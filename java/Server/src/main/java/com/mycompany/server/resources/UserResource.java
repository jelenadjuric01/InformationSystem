/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.resources;

import com.mycompany.server.JMSCommunication;
import commands.CreateUserCommand;
import commands.GetUserCommand;
import commands.UpdateUserEmailCommand;
import commands.UpdateUserTownCommand;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import responses.JMSResponse;
/**
 *
 * @author Jelena
 */
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    JMSCommunication communicator;

    @GET
    public Response getAllClients() {
        JMSResponse r = communicator.exchange(new GetUserCommand());
        if (!r.isSuccessful()) {
            return Response
                .serverError()
                .entity(r)
                .build();
        }
        return Response
            .ok(r)
            .build();
    }
    
    @POST
    public Response createUser(
            @FormParam("townId") int townId, @FormParam("name") String name,
            @FormParam("lastname") String lastname,
            @FormParam("email") String email, @FormParam("year") int year,
            @FormParam("sex") Character sex) {
        JMSResponse r = communicator.exchange(new CreateUserCommand(name, townId, lastname, email, year, sex));
        if (!r.isSuccessful()) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(r)
                .build();
        }
        return Response
            .ok(r)
            .build();
    }
    @PATCH
    @Path("{userId}")
    public Response updateUserEmail(
            @PathParam("userId") int userId,
            @FormParam("email") String email) {
        JMSResponse r = communicator.exchange(new UpdateUserEmailCommand(email, userId));
        if (!r.isSuccessful()) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(r)
                .build();
        }
        return Response
            .ok(r)
            .build();
    }
    
    @PATCH
    @Path("town/{userId}")
    public Response updateClient(
            @PathParam("userId") int userId,
            @FormParam("townId") int townId) {
        JMSResponse r = communicator.exchange(new UpdateUserTownCommand(townId, userId));
        if (!r.isSuccessful()) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(r)
                .build();
        }
        return Response
            .ok(r)
            .build();
    }
}
