package com.example.cardiacrecorder;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.annotation.NonNull;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class AddDataUnitTest {
    @Test
    public void addDataTest() {

        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();

        CardiacMeasurement cardiacMeasurement = new CardiacMeasurement();
        cardiacMeasurement.setComment("This is not good");
        cardiacMeasurement.setMeasuredDate("2/2/20");
        cardiacMeasurement.setDiastolicPressure(1000);
        cardiacMeasurement.setSystolicPressure(1000);
        cardiacMeasurement.setHeartRate(120);

        String key = mdatabase.push().getKey();

        mdatabase.child("measurements").child(key).setValue(cardiacMeasurement).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                assertTrue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                assertTrue(false);
            }
        });
    }
}
