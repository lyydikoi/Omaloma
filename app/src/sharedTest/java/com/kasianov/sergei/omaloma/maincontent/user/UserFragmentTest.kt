package com.kasianov.sergei.omaloma.maincontent.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.runner.RunWith
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.TextLayoutMode

/**
 * Integration test for the UserDetailsFragment screen.
 */

@RunWith(AndroidJUnit4::class)
@MediumTest
@LooperMode(LooperMode.Mode.PAUSED)
@TextLayoutMode(TextLayoutMode.Mode.REALISTIC)
class UserFragmentTest {
    /*private lateinit var repository: UserDataContract.UserRepository

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

        // THEN - Verify that we navigate to the UserDetailsFragment screen
        // val startDateData = (repository.getUser() as RequestResult.Success).data
        // assertEquals(startDateData.size, 1)
        // assertEquals(startDateData[0].startDate, "2017-12-05")
    }

    private fun launchFragment(navController: NavController?) {
        // val scenario = launchFragmentInContainer<UserDetailsFragment>(
        // Bundle(),
        // R.style.AppTheme
        // )
        // scenario.onFragment {
        // Navigation.setViewNavController(it.view!!, navController)
        // }
    }

    @Test
    fun emptyStartDate_IsNotSaved() {
    }*/
}
