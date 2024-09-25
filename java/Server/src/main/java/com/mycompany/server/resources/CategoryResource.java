/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.resources;

import com.mycompany.server.JMSCommunication;
import commands.CreateCategoryCommand;
import commands.GetCategoriesCommand;
import commands.GetVideoCategoryCommand;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
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
@Path("category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
    @Inject
    JMSCommunication communicator;
    
    @GET
    public Response getAllCategories() {
        JMSResponse r = communicator.exchange(new GetCategoriesCommand());
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
    @GET
    @Path("{videoId}")
    public Response getCategoriesForVideo(@PathParam("videoId") int videoId) {
        JMSResponse r = communicator.exchange(new GetVideoCategoryCommand(videoId));
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
    public Response createCategory(
            @FormParam("name") String name) {
        JMSResponse r = communicator.exchange(new CreateCategoryCommand(name));
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
