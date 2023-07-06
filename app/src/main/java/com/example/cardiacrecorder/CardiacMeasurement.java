package com.example.cardiacrecorder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Contains the information of a cardiac measurement
 */
public class CardiacMeasurement {
    private String id;
    private int year, month, date;
    private int hour, minute;
    private int systolicPressure;
    private int diastolicPressure;
    private int heartRate;
    private String comment;

    /**
     * Empty constructor is needed to read class object from firebase
     */
    public CardiacMeasurement() {}


    /**
     * Creates a new Measurement of specified data
     * @param id unique id of data
     * @param year year when measured
     * @param month month when measured
     * @param date date when measued
     * @param hour hour when measued
     * @param minute minute when measued
     * @param systolicPressure systolic pressure of the user in mm Hg
     * @param diastolicPressure diastolic pressure of the user in mm Hd
     * @param heartRate heart rate of the user in beats per min
     * @param comment any comment about the situation of the user
     */
    public CardiacMeasurement(String id, int year, int month, int date, int hour, int minute, int systolicPressure, int diastolicPressure, int heartRate, String comment) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.comment = comment;
    }

    /**
     * Returns the year when measured
     * @return year when measured
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the measurement
     * @param year year when measured
     */
    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
