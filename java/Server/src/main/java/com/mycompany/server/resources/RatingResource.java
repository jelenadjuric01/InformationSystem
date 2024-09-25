/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.resources;

import com.mycompany.server.JMSCommunication;
import commands.CreateRatingCommand;
import commands.CreateUserCommand;
import commands.DeleteRatingCommand;
import commands.DeleteVideoCommand;
import commands.GetUserCommand;
import commands.GetVideoRatingsCommand;
import commands.UpdateRatingRateCommand;
import commands.UpdateUserEmailCommand;
import commands.UpdateUserTownCommand;
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
@Path("rating")
@Produces(MediaType.APPLICATION_JSON)
public class RatingResource {
      @Inject
    JMSCommunication communicator;

    @GET
    @Path("{videoId}")
    public Response getVideoRating(@PathParam("videoId") int videoId) {
        JMSResponse r = communicator.exchange(new GetVideoRatingsCommand(videoId));
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
            @FormParam("rate") int rate,
            @FormParam("userId") int userId,@FormParam("videoId") int videoId) {
        JMSResponse r = communicator.exchange(new CreateRatingCommand(rate,userId,videoId));
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
    @Path("{ratingId}")
    public Response updateVideoRating(
            @PathParam("ratingId") int ratingId,@FormParam("rate") int rate) {
        JMSResponse r = communicator.exchange(new UpdateRatingRateCommand(ratingId, rate));
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
    @Path("{ratingId}")
    public Response deleteRating(
            @PathParam("ratingId") int ratingId) {
        JMSResponse r = communicator.exchange(new DeleteRatingCommand(ratingId));
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
}
