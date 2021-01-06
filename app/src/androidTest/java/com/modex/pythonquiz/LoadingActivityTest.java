package com.modex.pythonquiz;


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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("deprecation")
@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoadingActivityTest {

    @Rule
    public ActivityTestRule<LoadingActivity> mActivityTestRule = new ActivityTestRule<>(LoadingActivity.class);

    @Test
    public void loadingActivityTest() {
        ViewInteraction relativeLayout = onView(
                allOf(withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonConfirm), withText("Play"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radioButton4), withText("quit()"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.radioButton2), withText("20"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatRadioButton2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withContentDescription("TODO"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.popUpClose), withContentDescription("close"),
                        childAtPosition(
                                allOf(withId(R.id.image),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonConfirm), withText("Play"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_difficulty),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner_difficulty),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonConfirm), withText("Play"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatRadioButton3 = onView(
                allOf(withId(R.id.radioButton1), withText("A"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatRadioButton3.perform(click());

        ViewInteraction appCompatRadioButton4 = onView(
                allOf(withId(R.id.radioButton2), withText("po"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatRadioButton4.perform(click());
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
