//package com.example.cardiacrecorder;
//
//import java.util.ArrayList;
//
//public class FirebaseMockTest {
//    private ArrayList<CardiacMeasurement> records = new ArrayList<>();
//    public void add(CardiacMeasurement record){
//        if(records.contains(record)){
//            throw new IllegalArgumentException();
//        }
//        records.add(record);
//    }
//    public ArrayList<CardiacMeasurement> getRecords(){
//        ArrayList<CardiacMeasurement> recordList = records;
//        return recordList;
//    }
//    public void  delete(CardiacMeasurement record){
//        if(!records.contains(record)){
//            throw new IllegalArgumentException();
//        }
//        else{
//            records.remove(record);
//        }
//    }
//    public void Update(CardiacMeasurement record){
//        if(records.contains(record)){
//            records.remove(record);
//            record.setHeartRate(100);
//            record.setSystolicPressure(100);
//            record.setDiastolicPressure(100);
//            record.setComment("good condition");
//            record.setMeasuredDate("05/07/2023");
//            record.setId("277");
//            records.add(record);
//        }
//        else{
//            throw new IllegalArgumentException();
//        }
//
//    }
//    public int get_size(){
//        return records.size();
//    }
//}
