package com.jlopezinc;

import com.jlopezinc.database.BloodPressureEntity;
import com.jlopezinc.database.AccountEntity;
import com.jlopezinc.model.BloodPressure;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("v1/bloodpressure/")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BloodPressureResource extends ResourceSupport {

    @GET
    public List<BloodPressure> get(@Context SecurityContext ctx, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("10") int pageSize){
        String uuid = getUuid();
        return BloodPressureEntity.findByUserId(uuid, page, pageSize).stream().map(b -> {
            BloodPressure bloodPressure = new BloodPressure();
            bloodPressure.setCreatedOn(b.createdOn);
            bloodPressure.setSystolic(b.systolic);
            bloodPressure.setDiastolic(b.diastolic);
            bloodPressure.setHeartrate(b.heartRate);
            return bloodPressure;
        }).collect(Collectors.toList());
    }


    @GET
    @Path("count")
    public long getCount(@Context SecurityContext ctx){
        String uuid = getUuid();
        return BloodPressureEntity.countByUserId(uuid);
    }

    @POST
    @Transactional
    public Response post (@Context SecurityContext ctx, BloodPressure bloodPressure){
        String uuid = getUuid();
        AccountEntity accountEntity = AccountEntity.findByExternal(uuid);
        BloodPressureEntity bloodPressureEntity = new BloodPressureEntity();
        bloodPressureEntity.account = accountEntity;
        bloodPressureEntity.systolic = bloodPressure.getSystolic();
        bloodPressureEntity.diastolic = bloodPressure.getDiastolic();
        bloodPressureEntity.heartRate = bloodPressure.getHeartrate();
        bloodPressureEntity.createdOn = new Date();
        BloodPressureEntity.persist(bloodPressureEntity);

        return Response.created(null).build();
    }

}
