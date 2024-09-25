/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.resources;

import com.mycompany.server.JMSCommunication;
import commands.CreatePackageCommand;
import commands.GetPackageCommand;
import commands.UpdatePackagePriceCommand;
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
@Path("package")
@Produces(MediaType.APPLICATION_JSON)
public class PackageResource {
    @Inject
    JMSCommunication communicator;
    
    @GET
    public Response getAllPlaces() {
        JMSResponse r = communicator.exchange(new GetPackageCommand());
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
            @FormParam("price") int price) {
        JMSResponse r = communicator.exchange(new CreatePackageCommand(price));
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
    @Path("{packageId}")
    public Response updatePackagePrice(@PathParam("packageId") int packageId,
            @FormParam("price") int price){
           JMSResponse r = communicator.exchange(new UpdatePackagePriceCommand(packageId, price));
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
