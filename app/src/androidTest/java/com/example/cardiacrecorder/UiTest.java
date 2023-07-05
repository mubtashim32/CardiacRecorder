package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;

import androidx.annotation.NonNull;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.lang.*;

@RunWith(AndroidJUnit4.class)
public class UiTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void testAdd() {
        onView(withId(R.id.addMeasurement)).perform(click());

        String systolicPressure = "1005";
        String diastolicPressure = "1005";
        String heartRate = "1005";
        String comment = "1005";

        onView(withId(R.id.systolicPressure)).perform(typeText(systolicPressure));
        Espresso.pressBack();
        onView(withId(R.id.diastolicPressure)).perform(typeText(diastolicPressure));
        Espresso.pressBack();
        onView(withId(R.id.heartRate)).perform(typeText(heartRate));
        Espresso.pressBack();
        onView(withId(R.id.comment)).perform(typeText(comment));
        Espresso.pressBack();
        onView(withId(R.id.addData)).perform(click());

        FirebaseDatabase.getInstance().getReference().child("measurements").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean found = false;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CardiacMeasurement cardiacMeasurement = dataSnapshot.getValue(CardiacMeasurement.class);
                    if (systolicPressure.equals(Integer.toString(cardiacMeasurement.getSystolicPressure())) &&
                            diastolicPressure.equals(Integer.toString(cardiacMeasurement.getDiastolicPressure())) &&
                            heartRate.equals(Integer.toString(cardiacMeasurement.getHeartRate())) &&
                            comment.equals(cardiacMeasurement.getComment())
                    ) {
                        found = true;
                    }
                }
                Assert.assertTrue(found);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Assert.fail();
    }

    @Test
    public void testShow() {

    }

    public void testUpdate() {

    }
    public void testDelete() {

    }
}
