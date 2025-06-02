package com.example.appfortesting

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class TextInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun textInput_displaysTypedText(){
        composeTestRule.setContent {
            TextInput()
        }

        val input = "Hola mundo"

        composeTestRule
            .onNodeWithTag("inputField")
            .performTextInput(input)

        composeTestRule
            .onNodeWithTag("outputText")
            .assertTextEquals("Typed: $input")
    }
}