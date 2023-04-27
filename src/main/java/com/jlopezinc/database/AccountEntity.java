package com.jlopezinc.database;


import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name="account")
public class AccountEntity extends PanacheEntity {
    @Column(name = "external_id")
    public String externalId;
    @Column(name="created_on")
    public Date createdOn;
    @Column(name="last_login_date")
    public Date lastLoginDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<BloodPressureEntity> bloodPressures;

    public static AccountEntity findByExternal(String externalId){
        return AccountEntity.find("externalId", externalId).firstResult();
    }
}
