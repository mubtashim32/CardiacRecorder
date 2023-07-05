package com.example.cardiacrecorder;

import java.util.ArrayList;

public class FirebaseMockTest {
    private ArrayList<CardiacMeasurement> records = new ArrayList<>();
    public void add(CardiacMeasurement record){
        if(records.contains(record)){
            throw new IllegalArgumentException();
        }
        records.add(record);
    }
    public ArrayList<CardiacMeasurement> getRecords(){
        ArrayList<CardiacMeasurement> recordList = records;
        return recordList;
    }
    public void  delete(CardiacMeasurement record){
        if(!records.contains(record)){
            throw new IllegalArgumentException();
        }
        else{
            records.remove(record);
        }
    }
    public void Update(CardiacMeasurement record){
        if(records.contains(record)){
            records.remove(record);
            record.setHeartRate(100);
            record.setSystolicPressure(100);
            record.setDiastolicPressure(100);
            record.setComment("good condition");
            record.setYear(2023);
            record.setMonth(7);
            record.setDate(5);
            record.setHour(12);
            record.setMinute(05);
            record.setId("277");
            records.add(record);
        }
        else{
            throw new IllegalArgumentException();
        }

    }
    public int get_size(){
        return records.size();
    }
}
