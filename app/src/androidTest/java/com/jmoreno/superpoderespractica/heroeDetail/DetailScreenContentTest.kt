package com.jmoreno.superpoderespractica.heroeDetail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.jmoreno.superpoderespractica.model.Comics
import com.jmoreno.superpoderespractica.ui.heroeDetail.ComicsBlock
import org.junit.Rule
import org.junit.Test


class DetailScreenContentTest{
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun given_screen_when_launched_then_title_of_comics_is_displayed() {
        with(composeRule){
            // Given
            setContent {
                ComicsBlock(emptyList())
            }
            // When
            Thread.sleep(3000)
            // WHEN
            onNodeWithTag("Title comic tag").assertExists().assertIsDisplayed()
        }
    }
}