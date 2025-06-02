package com.example.appfortesting

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToNode
import org.junit.Rule
import org.junit.Test

class ScrollTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun list_scrollsToItem(){
        composeTestRule.setContent {
            ItemList()
        }

        //Hace Scroll hasta el item 49
        composeTestRule
            .onNodeWithTag("list")
            .performScrollToNode(hasTestTag("item_49"))

        composeTestRule
            .onNodeWithTag("item_49")
            .assertExists()
            .assertTextEquals("Item #49")
    }
}