package com.android.watson.translator;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void UITest() {
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.sourceText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Hello Tarik"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.targetLangSelector),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction materialTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(3);
        materialTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("French"),
                        childAtPosition(
                                allOf(withId(R.id.targetLangSelector),
                                        childAtPosition(
                                                withId(R.id.main),
                                                3)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("French")));

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.translateButton), withText("Translate"),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.targetText),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        textView2.check(matches(withText("Bonjour Tarik")));

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.targetLangSelector),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        materialTextView2.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.text1), withText("German"),
                        childAtPosition(
                                allOf(withId(R.id.targetLangSelector),
                                        childAtPosition(
                                                withId(R.id.main),
                                                3)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("German")));

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.translateButton), withText("Translate"),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.targetText),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        textView4.check(matches(withText("Hallo Tarik")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}


