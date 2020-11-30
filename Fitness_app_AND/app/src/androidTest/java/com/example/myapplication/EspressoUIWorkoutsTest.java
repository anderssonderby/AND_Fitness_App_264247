package com.example.myapplication;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoUIWorkoutsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    // Improvement for the future: Use idling resources instead of Thread.sleep
    @Test
    public void testLogWorkout() throws InterruptedException {
        Thread.sleep(9000); // Has to sleep before running the test because of Google Authentication occupying the root view during startup

        onView(withId(R.id.log_new_button)).perform(click());
        onView(withId(R.id.editTextMuscleGroup)).perform(replaceText("Legs"), closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(replaceText("Squats, Front squats"), closeSoftKeyboard());
        onView(withId(R.id.button_log)).perform(click());
        onView(withId(R.id.button_back)).perform(click());
        onView(withId(R.id.workout_desc)).check(matches(withText("Squats, Front squats")));
    }

}
