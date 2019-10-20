package com.jlopezinc.model;

import java.util.Date;

import lombok.Data;

@Data
public class BloodPressure {
    private int systolic;
    private int diastolic;
    private int heartrate;
    private Date createdOn;
}
