package com.example.cardiacrecorder;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import org.junit.jupiter.api.Test;

public class FinalUnitTest {
    @Test
    public void AddTest(){
        CardiacMeasurement record = new CardiacMeasurement();
        record.setHeartRate(100);
        record.setSystolicPressure(100);
        record.setDiastolicPressure(100);
        record.setComment("bad condition");
        record.setYear(2023);
        record.setMonth(7);
        record.setDate(5);
        record.setHour(12);
        record.setMinute(05);
        record.setId("2");

        CardiacMeasurement record1 = new CardiacMeasurement();
        record1.setHeartRate(100);
        record1.setSystolicPressure(100);
        record1.setDiastolicPressure(100);
        record1.setComment("good condition");
        record1.setYear(2023);
        record1.setMonth(7);
        record1.setDate(5);
        record1.setHour(12);
        record1.setMinute(05);
        record1.setId("1");

        FirebaseMockTest firebaseMockTest = new FirebaseMockTest();
        firebaseMockTest.add(record);
        assertEquals(1, firebaseMockTest.get_size());
        firebaseMockTest.add(record1);
        assertEquals(2, firebaseMockTest.get_size());
    }
    @Test
    public void DeleteTest(){
        CardiacMeasurement record = new CardiacMeasurement();
        record.setHeartRate(100);
        record.setSystolicPressure(100);
        record.setDiastolicPressure(100);
        record.setComment("bad condition");
        record.setYear(2023);
        record.setMonth(7);
        record.setDate(5);
        record.setHour(12);
        record.setMinute(05);
        record.setId("2");

        CardiacMeasurement record1 = new CardiacMeasurement();
        record1.setHeartRate(100);
        record1.setSystolicPressure(100);
        record1.setDiastolicPressure(100);
        record1.setComment("good condition");
        record1.setYear(2023);
        record1.setMonth(7);
        record1.setDate(5);
        record1.setHour(12);
        record1.setMinute(05);
        record1.setId("1");

        FirebaseMockTest firebaseMockTest = new FirebaseMockTest();
        firebaseMockTest.add(record);
        firebaseMockTest.add(record1);
        assertEquals(2, firebaseMockTest.get_size());
        firebaseMockTest.delete(record);
        assertEquals(1, firebaseMockTest.get_size());
        assertFalse(firebaseMockTest.getRecords().contains(record));
    }
    @Test
    public void TestAddException(){
        CardiacMeasurement record = new CardiacMeasurement();
        record.setHeartRate(100);
        record.setSystolicPressure(100);
        record.setDiastolicPressure(100);
        record.setComment("bad condition");
        record.setYear(2023);
        record.setMonth(7);
        record.setDate(5);
        record.setHour(12);
        record.setMinute(05);
        record.setId("2");

        CardiacMeasurement record1 = new CardiacMeasurement();
        record1.setHeartRate(100);
        record1.setSystolicPressure(100);
        record1.setDiastolicPressure(100);
        record1.setComment("good condition");
        record1.setYear(2023);
        record1.setMonth(7);
        record1.setDate(5);
        record1.setHour(12);
        record1.setMinute(05);
        record1.setId("1");

        FirebaseMockTest firebaseMockTest = new FirebaseMockTest();
        firebaseMockTest.add(record);
        firebaseMockTest.add(record1);

        assertThrows(IllegalArgumentException.class,()->{
            firebaseMockTest.add(record1);
        });
    }
    @Test
    public void TestDeleteException(){
        CardiacMeasurement record = new CardiacMeasurement();
        record.setHeartRate(100);
        record.setSystolicPressure(100);
        record.setDiastolicPressure(100);
        record.setComment("bad condition");
        record.setYear(2023);
        record.setMonth(7);
        record.setDate(5);
        record.setHour(12);
        record.setMinute(05);
        record.setId("2");

        CardiacMeasurement record1 = new CardiacMeasurement();
        record1.setHeartRate(100);
        record1.setSystolicPressure(100);
        record1.setDiastolicPressure(100);
        record1.setComment("good condition");
        record1.setYear(2023);
        record1.setMonth(7);
        record1.setDate(5);
        record1.setHour(12);
        record1.setMinute(05);
        record1.setId("1");
        FirebaseMockTest firebaseMockTest = new FirebaseMockTest();
        firebaseMockTest.add(record);
        firebaseMockTest.add(record1);
        firebaseMockTest.delete(record);

        assertThrows(IllegalArgumentException.class,()->{
            firebaseMockTest.delete(record);
        });
    }
    @Test
    public void UpdateTest(){
        CardiacMeasurement record = new CardiacMeasurement();
        record.setHeartRate(100);
        record.setSystolicPressure(100);
        record.setDiastolicPressure(100);
        record.setComment("bad condition");
        record.setYear(2023);
        record.setMonth(7);
        record.setDate(5);
        record.setHour(12);
        record.setMinute(05);
        record.setId("2");

        CardiacMeasurement record1 = new CardiacMeasurement();
        record1.setHeartRate(100);
        record1.setSystolicPressure(100);
        record1.setDiastolicPressure(100);
        record1.setComment("good condition");
        record1.setYear(2023);
        record1.setMonth(7);
        record1.setDate(5);
        record1.setHour(12);
        record1.setMinute(05);
        record1.setId("1");

        FirebaseMockTest firebaseMockTest = new FirebaseMockTest();
        firebaseMockTest.add(record);
        firebaseMockTest.add(record1);
        firebaseMockTest.Update(record1);

        record1.setId("277");

        assertTrue(firebaseMockTest.getRecords().contains(record));
    }
}
