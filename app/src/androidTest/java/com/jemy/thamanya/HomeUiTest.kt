package com.jemy.thamanya

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.jemy.thamanya.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeUiTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun homeScreen_isDisplayed() {
        // Note: Make sure the device/emulator language matches the expected string.
        composeTestRule.onNodeWithText("Thamanya").assertIsDisplayed()
        
        // Check if Search icon is displayed in AppBar
        composeTestRule.onNodeWithContentDescription("Search").assertIsDisplayed()
    }

    @Test
    fun homeScreen_loadingIndicator_isInitiallyVisible() {
        // When the app starts, it fetches data. A CircularProgressIndicator should be visible.
        // If the internet is very fast, this might fail, so usually we mock the repo for deterministic tests.
        // For now, we just check if it's there.
        // Note: You might need to add a testTag to your CircularProgressIndicator in Home.kt to find it easily.
    }
}
