package com.jlopezinc;

import com.jlopezinc.database.BloodPressureEntity;
import com.jlopezinc.model.BloodPressure;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("v1/bloodpressure/{uuid}")
@Produces("application/json")
@Consumes("application/json")
public class BloodPressureResource {

    @GET
    public List<BloodPressure> get(@PathParam("uuid") String uuid){
        // todo map to user
        int userId = 1;
        return BloodPressureEntity.findByUserId(userId).stream().map(b -> {
            BloodPressure bloodPressure = new BloodPressure();
            bloodPressure.setCreatedOn(b.created_on);
            bloodPressure.setSystolic(b.systolic);
            bloodPressure.setDiastolic(b.diastolic);
            bloodPressure.setHeartrate(b.heartrate);
            return bloodPressure;
        }).collect(Collectors.toList());
    }

    @POST
    @Transactional
    public Response post (@PathParam("uuid") String uuid, BloodPressure bloodPressure){
        // todo map to user
        int userId = 1;
        BloodPressureEntity bloodPressureEntity = new BloodPressureEntity();
        bloodPressureEntity.user_id = userId;
        bloodPressureEntity.systolic = bloodPressure.getSystolic();
        bloodPressureEntity.diastolic = bloodPressure.getDiastolic();
        bloodPressureEntity.heartrate = bloodPressure.getHeartrate();
        bloodPressureEntity.created_on = new Date();
        BloodPressureEntity.persist(bloodPressureEntity);

        return Response.created(null).build();
    }
}
