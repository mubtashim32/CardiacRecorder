package com.example.cardiacrecorder;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import org.junit.jupiter.api.Test;

public class FinalUnitTest {
    @Test
    public void AddTest(){
        CardiacMeasurement record = new CardiacMeasurement("1", "05/07/2023", 100, 100, 100, "good");
        CardiacMeasurement record1 = new CardiacMeasurement("2", "05/07/2023", 100, 100, 100, "good");

        FirebaseMockTest firebaseMockTest = new FirebaseMockTest();
        firebaseMockTest.add(record);
        assertEquals(1, firebaseMockTest.get_size());
        firebaseMockTest.add(record1);
        assertEquals(2, firebaseMockTest.get_size());
    }
    @Test
    public void DeleteTest(){
        CardiacMeasurement record = new CardiacMeasurement("1", "05/07/2023", 100, 100, 100, "good");
        CardiacMeasurement record1 = new CardiacMeasurement("2", "05/07/2023", 100, 100, 100, "good");

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
        CardiacMeasurement record = new CardiacMeasurement("1", "05/07/2023", 100, 100, 100, "good");
        CardiacMeasurement record1 = new CardiacMeasurement("2", "05/07/2023", 100, 100, 100, "good");

        FirebaseMockTest firebaseMockTest = new FirebaseMockTest();
        firebaseMockTest.add(record);
        firebaseMockTest.add(record1);

        assertThrows(IllegalArgumentException.class,()->{
            firebaseMockTest.add(record1);
        });
    }
    @Test
    public void TestDeleteException(){
        CardiacMeasurement record = new CardiacMeasurement("1", "05/07/2023", 100, 100, 100, "good");
        CardiacMeasurement record1 = new CardiacMeasurement("2", "05/07/2023", 100, 100, 100, "good");

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
        CardiacMeasurement record = new CardiacMeasurement("1", "05/07/2023", 100, 100, 100, "good");
        CardiacMeasurement record1 = new CardiacMeasurement("2", "05/07/2023", 100, 100, 100, "good");

        FirebaseMockTest firebaseMockTest = new FirebaseMockTest();
        firebaseMockTest.add(record);
        firebaseMockTest.add(record1);
        firebaseMockTest.Update(record1);

        record.setHeartRate(100);
        record.setSystolicPressure(100);
        record.setDiastolicPressure(100);
        record.setComment("good condition");
        record.setMeasuredDate("05/07/2023");
        record.setId("277");

        assertTrue(firebaseMockTest.getRecords().contains(record));
    }
}
