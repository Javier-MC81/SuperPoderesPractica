package com.jmoreno.superpoderespractica.ui.heroeDetail

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsToggleable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import org.junit.Rule
import org.junit.Test


class ImageAndSwitchBlockTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun imageAndSwitchBlock_SwitchToChangeFavorite() {
        with(composeRule){

            val inicial = LocalHero(
                id = 101,
                name = "Hulk",
                thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/6/30/4ce69c2246c21.jpg",
                favorite = false
            )
            var final: LocalHero? = null
            val onSuperHeroListClicked: (LocalHero) -> Unit = { hero ->
                final = hero
            }

            setContent {
                ImageAndSwitchBlock(inicial, onSuperHeroListClicked)
            }
            onNodeWithContentDescription("${inicial.name} photo")
                .assertIsDisplayed()

            onNodeWithTag("Favorite Switch")
                .performClick()

            final?.let { assert(it.favorite) }
        }
        }

}
/*
En este test de UI definimos el estado inicial de un heroe y el del switch. Después comprobamos que la imagen y el switch se muestran
correctamente y clicamos para cambiar el estado de favorito del héroe. Finalmente comprobamos que ha cambiado el estado.
 */
