package com.example.appfortesting

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class VisibilityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun visibilityText_showsOnlyWhenTrue(){
        composeTestRule.setContent {
            ShowIfNeeded(true)
        }

        composeTestRule
            .onNodeWithTag("visibilityText")
            .assertExists()
    }
}