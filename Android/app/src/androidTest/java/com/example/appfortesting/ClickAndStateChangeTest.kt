package com.example.appfortesting

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ClickAndStateChangeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun toggleTest_isDisplayedAfterButtonClick(){
        composeTestRule.setContent {
            ToggleText()
        }

        //Garantizar que al inicio no debe existir
        composeTestRule
            .onNodeWithTag("toggledText")
            .assertDoesNotExist()

        // Momento en que doy click al boton
        composeTestRule
            .onNodeWithTag("toggleButton")
            .performClick()

        //Ahora debe existir
        composeTestRule
            .onNodeWithTag("toggledText")
            .assertExists()
    }
}