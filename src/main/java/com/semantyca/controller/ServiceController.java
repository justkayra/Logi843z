package com.semantyca.controller;

import com.semantyca.service.DatabaseService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/")
public class ServiceController {

    @Inject
    private DatabaseService applicationDataService;

    @GET
    @Path("/populate_users")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean populateUsers() {
        return  applicationDataService.populateUsers();
    }

    @GET
    @Path("/populate_assignees")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean populateAssignees() {
        return  applicationDataService.populateAssignees();
    }

    @DELETE
    @Path("/purge_users")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean purge() {
        return  applicationDataService.purgeUsers();
    }

    @DELETE
    @Path("/purge_assignees")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean purgeAssignees() {
        return  applicationDataService.purgeAssignees();
    }

}
