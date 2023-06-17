package com.jmoreno.superpoderespractica.navigation

import com.jmoreno.superpoderespractica.navigation.Screens.DetailScreen.ARG_ID

sealed class Screens(val route: String) {

    object SuperheroListScreen : Screens(SCREEN1_BASE_ROUTE)
    object DetailScreen : Screens(SCREEN2_ROUTE_TEMPLATE){
        const val  ARG_ID = "heroeId"

        fun createRouteWithArgs(id: Long): String{
            return SCREEN_ROUTE_TO_FORMAT.format(id)
        }
    }

    companion object {
        private const val SCREEN1_BASE_ROUTE = "SuperheroListScreen"
        private const val SCREEN2_BASE_ROUTE = "DetailScreen"
        private const val SCREEN2_ROUTE_TEMPLATE = "$SCREEN2_BASE_ROUTE/{$ARG_ID}"
        private const val SCREEN_ROUTE_TO_FORMAT = "$SCREEN2_BASE_ROUTE/%s"

    }
}