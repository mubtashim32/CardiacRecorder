package com.example.cardiacrecorder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CardiacMeasurement {
    private String id;
    private String measuredDate;
    private int systolicPressure;
    private int diastolicPressure;
    private int heartRate;
    private String comment;

    public CardiacMeasurement() {}

    public CardiacMeasurement(String id, String measuredDate, int systolicPressure, int diastolicPressure, int heartRate, String comment) {
        this.id = id;
        this.measuredDate = measuredDate;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeasuredDate() {
        return measuredDate;
    }

    public void setMeasuredDate(String measuredDate) {
        this.measuredDate = measuredDate;
    }

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
