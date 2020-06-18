package com.example.firstappv2;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.firstappv2.fragments.main.MainFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<NavMainActivity> activityActivityTestRule = new ActivityTestRule<>(NavMainActivity.class);

    @Test
    public void checkFirst() {
        onView(withText(R.string.app_header)).check(matches(isDisplayed()));
    }

    @Test
    public void checkFirstSecond() {
        onView(withId(R.id.text_input_thing)).perform(typeText("Paris"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.deepButton)).perform(click());
        onView(withText("Да")).perform(click());
        onView(withId(R.id.city)).check(matches(withText("Paris")));
    }

    @Test
    public void checkList() {
        onView(withId(R.id.text_input_thing)).perform(typeText("Paris"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.deepButton)).perform(click());
        onView(withText("Да")).perform(click());

        onView(withContentDescription("Открыть панель навигации")).perform(click());
        onView(withText("История погоды")).perform(click());
        onView(withId(R.id.city)).check(matches(withText("Paris")));
    }
}


