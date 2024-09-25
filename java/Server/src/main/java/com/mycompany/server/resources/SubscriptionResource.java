/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.resources;

import com.mycompany.server.JMSCommunication;
import commands.CreateCategoryCommand;
import commands.CreateSubCommand;
import commands.GetCategoriesCommand;
import commands.GetSubUserCommand;
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
@Path("subscription")
@Produces(MediaType.APPLICATION_JSON)
public class SubscriptionResource {
        @Inject
    JMSCommunication communicator;
    
    @GET
    @Path("{userId}")
    public Response getAllSubs(@PathParam("userId") int userId) {
        JMSResponse r = communicator.exchange(new GetSubUserCommand(userId));
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
    public Response createSub(
            @FormParam("packageId") int  packageId,
            @FormParam("userId") int userId) {
        JMSResponse r = communicator.exchange(new CreateSubCommand(packageId,userId));
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
