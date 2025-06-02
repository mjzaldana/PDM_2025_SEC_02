package com.example.appfortesting

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test

class GreetingTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun greeting_displayCorrectText(){
        composeTestRule.setContent {
            Greeting(
                name = "Android",
                modifier = Modifier.testTag("greetingText")
            )
        }

        composeTestRule
            .onNodeWithTag("greetingText")
            .assertExists()
            .assertTextEquals("Hello Android!")
    }
}