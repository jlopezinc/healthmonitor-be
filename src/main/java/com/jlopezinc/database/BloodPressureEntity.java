package com.jlopezinc.database;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@Entity(name = "blood_pressure")
public class BloodPressureEntity extends PanacheEntity {
    public Integer user_id;
    public Integer systolic;
    public Integer diastolic;
    public Integer heartrate;
    public Date created_on;

    public static List<BloodPressureEntity> findByUserId (Integer userId){
        return list("user_id", userId);
    }

    public static List<BloodPressureEntity> findByUserId (Integer userId, int page, int pageSize){
        final PanacheQuery<BloodPressureEntity> user_id = BloodPressureEntity.find("user_id", userId);
        return user_id.page(Page.of(page, pageSize)).list();
    }
}
