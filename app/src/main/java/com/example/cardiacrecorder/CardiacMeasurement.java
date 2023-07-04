package com.example.cardiacrecorder;

import java.util.Calendar;
import java.util.Date;

public class CardiacMeasurement {
    private Date measuredDate;
    private Calendar measuredTime;
    private int systolicPressure;
    private int diastolicPressure;
    private int heartRate;
    private String comment;

    public CardiacMeasurement(Date measuredDate, Calendar measuredTime, int systolicPressure, int diastolicPressure, int heartRate, String comment) {
        this.measuredDate = measuredDate;
        this.measuredTime = measuredTime;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.comment = comment;
    }

    public Date getMeasuredDate() {
        return measuredDate;
    }

    public void setMeasuredDate(Date measuredDate) {
        this.measuredDate = measuredDate;
    }

    public Calendar getMeasuredTime() {
        return measuredTime;
    }

    public void setMeasuredTime(Calendar measuredTime) {
        this.measuredTime = measuredTime;
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
