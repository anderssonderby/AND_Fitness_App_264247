package com.example.myapplication;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoUIExercisesTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    // Turn off animations before performing this test
    // Improvement for the future: Use idling resources instead of Thread.sleep
    @Test
    public void testBackSquatExerciseName() throws InterruptedException {
        Thread.sleep(9000); // Has to sleep before running the test because of Google Authentication occupying the root view during startup

        onView(withId(R.id.exercisesFragment)).perform(click());

        Thread.sleep(1000); // Wait for recyclerview to populate
        onView(withId(R.id.exercises_rv)).perform(RecyclerViewActions.scrollToPosition(3));
        onView(withId(R.id.exercises_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.tv_name)).check(matches(withText("Back Squat")));

    }

}
