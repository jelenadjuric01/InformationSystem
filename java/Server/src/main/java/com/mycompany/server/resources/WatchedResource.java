/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.resources;

import com.mycompany.server.JMSCommunication;
import commands.CreateSubCommand;
import commands.CreateWatchedCommand;
import commands.GetSubUserCommand;
import commands.GetWatchedVideoCommand;
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
@Path("watched")
@Produces(MediaType.APPLICATION_JSON)
public class WatchedResource {
    @Inject
    JMSCommunication communicator;
    
    @GET
    @Path("{videoId}")
    public Response getAllSubs(@PathParam("videoId") int videoId) {
        JMSResponse r = communicator.exchange(new GetWatchedVideoCommand(videoId));
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
            @FormParam("start") int start,@FormParam("seconds") int  seconds,
            @FormParam("userId") int userId,@FormParam("videoId") int videoId) {
        JMSResponse r = communicator.exchange(new CreateWatchedCommand(start,seconds,userId,videoId));
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
