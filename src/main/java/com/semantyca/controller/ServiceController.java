package com.semantyca.controller;

import com.semantyca.dto.IPage;
import com.semantyca.service.ApplicationDataService;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/populate")
@Singleton
public class ServiceController {

    private ApplicationDataService applicationDataService = new ApplicationDataService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IPage sayHello() {
        return  applicationDataService.populate();
    }

}
