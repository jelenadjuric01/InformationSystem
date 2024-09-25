/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.resources;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import commands.CreateTownCommand;
import commands.GetTownCommand;
import responses.JMSResponse;
import com.mycompany.server.JMSCommunication;
/**
 *
 * @author Jelena
 */
@Path("town")
@Produces(MediaType.APPLICATION_JSON)
public class TownResource {
    @Inject
    JMSCommunication communicator;
    
    @GET
    public Response getAllPlaces() {
        JMSResponse r = communicator.exchange(new GetTownCommand());
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
    public Response createPlace(
            @FormParam("name") String name) {
        JMSResponse r = communicator.exchange(new CreateTownCommand(name));
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
