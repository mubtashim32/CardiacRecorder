package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class MainUiTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void add() throws InterruptedException {
        Thread.sleep(5000);
        int prev = getTotalItem();
        onView(withId(R.id.addMeasurement)).perform(click());
        onView(withId(R.id.dateText)).perform(typeText("10-05-1005"));
        Espresso.pressBack();
        onView(withId(R.id.timeText)).perform(typeText("10:05"));
        Espresso.pressBack();
        onView(withId(R.id.systolicPressure)).perform(typeText("1005"));
        Espresso.pressBack();
        onView(withId(R.id.diastolicPressure)).perform(typeText("1005"));
        Espresso.pressBack();
        onView(withId(R.id.heartRate)).perform(typeText("1005"));
        Espresso.pressBack();
        onView(withId(R.id.comment)).perform(typeText("1005"));
        Espresso.pressBack();
        onView(withId(R.id.addData)).perform(click());
        int current = getTotalItem();
        assertEquals(prev+1, current);
    }

    @Test
    public void update() throws InterruptedException {
        Thread.sleep(5000);
        int prev = getTotalItem();
        onView(withId(R.id.cardiacMeasurements))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.dateText)).perform(clearText());
        Thread.sleep(100);
        onView(withId(R.id.timeText)).perform(clearText());
        Thread.sleep(100);
        onView(withId(R.id.systolicPressure)).perform(clearText());
        Thread.sleep(100);
        onView(withId(R.id.diastolicPressure)).perform(clearText());
        Thread.sleep(100);
        onView(withId(R.id.heartRate)).perform(clearText());
        Thread.sleep(100);
        onView(withId(R.id.comment)).perform(clearText());
        Thread.sleep(100);

        onView(withId(R.id.dateText)).perform(typeText("10-05-1005"));
        Espresso.pressBack();
        onView(withId(R.id.timeText)).perform(typeText("10:05"));
        Espresso.pressBack();
        onView(withId(R.id.systolicPressure)).perform(typeText("1005"));
        Espresso.pressBack();
        onView(withId(R.id.diastolicPressure)).perform(typeText("1005"));
        Espresso.pressBack();
        onView(withId(R.id.heartRate)).perform(typeText("1005"));
        Espresso.pressBack();
        onView(withId(R.id.comment)).perform(typeText("1005"));
        Espresso.pressBack();
        onView(withId(R.id.update)).perform(click());

        int current = getTotalItem();
        assertEquals(prev, current);
    }

    @Test
    public void delete() throws InterruptedException {
        Thread.sleep(5000);
        int prev = getTotalItem();
        onView(withId(R.id.cardiacMeasurements))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.delete)).perform(click());
        int current = getTotalItem();
        assertEquals(prev, current);
    }

    private int getTotalItem(){
        final int[] initialItemCount = new int[1];
        activityScenarioRule.getScenario().onActivity(activity -> {
            if( ((RecyclerView)activity.findViewById(R.id.cardiacMeasurements)).getAdapter() != null ) {
                initialItemCount[0] = Objects.requireNonNull(((RecyclerView) activity.findViewById(R.id.cardiacMeasurements)).getAdapter()).getItemCount();
            }
        });
        return initialItemCount[0];
    }


    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }




}
