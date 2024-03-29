package com.jlopezinc.database;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

@Entity
@Table(name="blood_pressure")
public class BloodPressureEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    public AccountEntity account;

    public Integer systolic;
    public Integer diastolic;
    @Column(name = "heartrate")
    public Integer heartRate;
    @Column(name = "created_on")
    public Date createdOn;

    public static List<BloodPressureEntity> findByUserId (String userExternalId, int page, int pageSize){
        // equivalent to:
        // select bp from BloodPressureEntity bp inner join bp.account u where u.externalId = ?1
        return BloodPressureEntity.find("account.externalId", Sort.descending("createdOn"), userExternalId)
            .page(Page.of(page, pageSize)).list();
    }

    public static long countByUserId(String userExternalId){
        return BloodPressureEntity.count("account.externalId", userExternalId);
    }
}
