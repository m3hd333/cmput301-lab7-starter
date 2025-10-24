package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testActivitySwitch() {
        // Add a city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("New City"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Check if ShowActivity is displayed
        onView(withId(R.id.textView_cityName)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameConsistency() {
        String cityName = "Another City";
        // Add a city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(cityName));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Check if the city name is consistent
        onView(withId(R.id.textView_cityName)).check(matches(withText(cityName)));
    }

    @Test
    public void testBackButton() {
        // Add a city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Some City"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Click the back button
        onView(withId(R.id.button_back)).perform(click());

        // Check if we are back in MainActivity
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}
