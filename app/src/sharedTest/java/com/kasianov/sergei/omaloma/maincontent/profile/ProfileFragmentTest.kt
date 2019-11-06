package com.kasianov.sergei.omaloma.maincontent.profile

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.kasianov.sergei.omaloma.ProfileFragment
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.data.Result
import com.kasianov.sergei.omaloma.data.source.UserRepository
import com.kasianov.sergei.omaloma.maincontent.data.source.FakeUserRepository
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.TextLayoutMode

/**
 * Integration test for the ProfileFragment screen.
 */

@RunWith(AndroidJUnit4::class)
@MediumTest
@LooperMode(LooperMode.Mode.PAUSED)
@TextLayoutMode(TextLayoutMode.Mode.REALISTIC)
class ProfileFragmentTest {
    private lateinit var repository: UserRepository

    @Before
    fun initRepository() {
        repository = FakeUserRepository()
    }

    @Test
    fun validStartDate_IsSaved() {
        // GIVEN - On the profile screen
        val navController = mock(NavController::class.java)
        launchFragment(navController)

        // WHEN - Click on the "Open profile" button
        onView(withId(R.id.tv_profile_start_date)).perform(typeText("2017-12-05"))

        // THEN - Verify that we navigate to the ProfileFragment screen
        val startDateData = (repository.getUser as Result.Success).data
        assertEquals(startDateData.size, 1)
        assertEquals(startDateData[0].startDate, "2017-12-05")
    }

    private fun launchFragment(navController: NavController?) {
        val scenario = launchFragmentInContainer<ProfileFragment>(
            Bundle(),
            R.style.AppTheme
        )
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @Test
    fun emptyStartDate_IsNotSaved() {

    }



}