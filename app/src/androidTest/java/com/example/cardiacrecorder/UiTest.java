package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;

import static org.hamcrest.CoreMatchers.anything;

import androidx.annotation.NonNull;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
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


import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Rule;
import org.junit.Test;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

@RunWith(AndroidJUnit4.class)
public class UiTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule = new ActivityScenarioRule<>(HomeActivity.class);


    public void placeDummyInformation(String systolicPressure, String diastolicPressure, String heartRate, String comment) {
        onView(withId(R.id.systolicPressure)).perform(typeText(systolicPressure));
        Espresso.pressBack();
        onView(withId(R.id.diastolicPressure)).perform(typeText(diastolicPressure));
        Espresso.pressBack();
        onView(withId(R.id.heartRate)).perform(typeText(heartRate));
        Espresso.pressBack();
        onView(withId(R.id.comment)).perform(typeText(comment));
        Espresso.pressBack();

    }
    public void clearInformation() throws InterruptedException {
        onView(withId(R.id.systolicPressure)).perform(clearText());
        Thread.sleep(1000);
        onView(withId(R.id.diastolicPressure)).perform(clearText());
        Thread.sleep(1000);
        onView(withId(R.id.heartRate)).perform(clearText());
        Thread.sleep(1000);
        onView(withId(R.id.comment)).perform(clearText());
        Thread.sleep(1000);
    }
//    public boolean found;
//    public boolean checkDatabase(String systolicPressure, String diastolicPressure, String heartRate, String comment) throws InterruptedException {
//        found = false;
//        FirebaseDatabase.getInstance().getReference().child("measurements").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    CardiacMeasurement cardiacMeasurement = dataSnapshot.getValue(CardiacMeasurement.class);
//                    if (systolicPressure.equals(Integer.toString(cardiacMeasurement.getSystolicPressure())) &&
//                            diastolicPressure.equals(Integer.toString(cardiacMeasurement.getDiastolicPressure())) &&
//                            heartRate.equals(Integer.toString(cardiacMeasurement.getHeartRate())) &&
//                            comment.equals(cardiacMeasurement.getComment())
//                    ) {
//                        System.out.println("Hello");
//                        found = true;
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        Thread.sleep(5000);
//        return found;
//    }
//    @Test
//    public void testAdd() throws InterruptedException {
//        onView(withId(R.id.addMeasurement)).perform(click());
//        placeDummyInformation("1234", "2345", "3456", "4567");
//        onView(withId(R.id.addData)).perform(click());
//        Assert.assertTrue(checkDatabase("1234", "2345", "3456", "4567"));
//    }

    @Test
    public void testShow() throws InterruptedException {
        onView(withId(R.id.addMeasurement)).perform(click());
        placeDummyInformation("1234", "2345", "3456", "4567");
        onView(withId(R.id.addData)).perform(click());
        Espresso.pressBack();
        Thread.sleep(5000);
        onView(withId(R.id.cardiacMeasurements))
                .perform(actionOnItemAtPosition(1, click()));

        onView(withText("1234")).check(matches(isDisplayed()));
        onView(withText("2345")).check(matches(isDisplayed()));
        onView(withText("3456")).check(matches(isDisplayed()));
        onView(withText("4567")).check(matches(isDisplayed()));
    }

    @Test
    public void testUpdate() throws InterruptedException {
        activityScenarioRule.getScenario();
        Thread.sleep(5000);
        onView(withId(R.id.cardiacMeasurements))
                .perform(actionOnItemAtPosition(1, click()));
        clearInformation();
        placeDummyInformation("9876", "8765", "7654", "6543");
        onView(withId(R.id.update)).perform(click());
//        Assert.assertEquals(checkDatabase("9876", "8765", "7654", "6543"), "true");
        FirebaseDatabase.getInstance().getReference().child("measurements").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CardiacMeasurement cardiacMeasurement = dataSnapshot.getValue(CardiacMeasurement.class);
                    if (Integer.toString(cardiacMeasurement.getSystolicPressure()).equals("9876") &&
                            Integer.toString(cardiacMeasurement.getDiastolicPressure()).equals("8765") &&
                            Integer.toString(cardiacMeasurement.getHeartRate()).equals("7654") &&
                            cardiacMeasurement.getComment().equals("5678")
                    ) {
                        System.out.println("Hello");
                        Assert.assertTrue(true);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Assert.fail();
    }
    public void testDelete() {

    }
}
