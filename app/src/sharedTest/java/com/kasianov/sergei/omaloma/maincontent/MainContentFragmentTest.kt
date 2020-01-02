package com.kasianov.sergei.omaloma.maincontent

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.kasianov.sergei.omaloma.ui.maincontent.MainContentFragment
import com.kasianov.sergei.omaloma.MainContentFragmentDirections
import com.kasianov.sergei.omaloma.R
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.TextLayoutMode

/**
 * Integration test for the UserDetailsFragment screen.
 */

@RunWith(AndroidJUnit4::class)
@MediumTest
@LooperMode(LooperMode.Mode.PAUSED)
@TextLayoutMode(TextLayoutMode.Mode.REALISTIC)
class MainContentFragmentTest {

    @Test
    fun clickOpenProfileButton_navigateProfileFragment() {
        // GIVEN - On the home screen
        val scenario = launchFragmentInContainer<MainContentFragment>(Bundle(), R.style.AppTheme)
        val navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - Click on the "Open profile" button
        onView(withId(R.id.fab_open_profile)).perform(click())

        // THEN - Verify that we navigate to the UserDetailsFragment screen
        //Mockito.verify(navController).navigate(MainContentFragmentDirections.actionContentMainToProfile().actionId)

        // THEN - Verify that we navigate to the ProfileFragment screen
        //Mockito.verify(navController).navigate(MainContentFragmentDirections.actionContentMainToHolidaysList().actionId)

    }

}