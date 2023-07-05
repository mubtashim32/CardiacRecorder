package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;

import static org.hamcrest.CoreMatchers.anything;

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

    String systolicPressure = "1005";
    String diastolicPressure = "1005";
    String heartRate = "1005";
    String comment = "1005";
    public void placeDummyInformation() {
        onView(withId(R.id.systolicPressure)).perform(typeText(systolicPressure));
        Espresso.pressBack();
        onView(withId(R.id.diastolicPressure)).perform(typeText(diastolicPressure));
        Espresso.pressBack();
        onView(withId(R.id.heartRate)).perform(typeText(heartRate));
        Espresso.pressBack();
        onView(withId(R.id.comment)).perform(typeText(comment));
        Espresso.pressBack();
    }
    @Test
    public void testAdd() {
        onView(withId(R.id.addMeasurement)).perform(click());

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

    @Test
    public void testUpdate() throws InterruptedException {
        activityScenarioRule.getScenario();
        Thread.sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.cardiacMeasurements)).atPosition(0).perform(click());
    }
    public void testDelete() {

    }
}
