package com.jlopezinc.database;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
