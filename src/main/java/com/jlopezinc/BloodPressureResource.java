package com.jlopezinc;

import com.jlopezinc.model.BloodPressure;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("bloodpressure/{uuid}")
@Produces("application/json")
public class BloodPressureResource {

    @GET
    public List<BloodPressure> get(@PathParam("uuid") String uuid){
        return Collections.emptyList();
    }
}
