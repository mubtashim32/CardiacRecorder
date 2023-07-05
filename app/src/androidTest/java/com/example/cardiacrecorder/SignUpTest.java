package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import android.view.View;
import android.widget.AbsListView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class SignUpTest {
    @Rule
    public ActivityScenarioRule<SignupActivity> signupActivityActivityScenarioRule = new ActivityScenarioRule<>(SignupActivity.class);

    @Test
    public void signup() throws InterruptedException {
        onView(withId(R.id.name)).perform(typeText("admin"));
        Espresso.pressBack();
        onView(withId(R.id.email)).perform(typeText("admin@gmail.com"));
        Espresso.pressBack();
        onView(withId(R.id.password)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.register)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.home)).check(matches(isDisplayed()));
    }
}
