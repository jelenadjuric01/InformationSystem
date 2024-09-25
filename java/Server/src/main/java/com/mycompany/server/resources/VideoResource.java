/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.resources;

import com.mycompany.server.JMSCommunication;
import commands.*;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
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
@Path("video")
@Produces(MediaType.APPLICATION_JSON)
public class VideoResource {
    @Inject
    JMSCommunication communicator;

    @GET
    public Response getallVideos() {
        JMSResponse r = communicator.exchange(new GetVideoCommand());
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
    public Response createVideo(
            @FormParam("userId") int userId,
            @FormParam("name") String name,
            @FormParam("duration") int duration) {
        JMSResponse r = communicator.exchange(new CreateVideoCommand(userId, name,duration));
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
    @Path("{videoId}")
    public Response updateVideoName(
            @PathParam("videoId") int videoId,
            @FormParam("name") String name) {
        JMSResponse r = communicator.exchange(new UpdateVideoNameCommand(videoId, name));
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
    @DELETE
    @Path("{videoId}/{userId}")
    public Response deleteVideo(@PathParam("videoId") int videoId,@PathParam("userId") int userId) {
        JMSResponse r = communicator.exchange(new DeleteVideoCommand(videoId,userId));
        if (!r.isSuccessful()) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(r)
                .build();
        }
        return Response
            .ok(r)
            .build();
    }
    @POST
    @Path("{videoId}")
    public Response addCategory(@PathParam("videoId") int videoId,@FormParam("catId") int catId){
           JMSResponse r = communicator.exchange(new CreateBelongsCommand(videoId, catId));
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
