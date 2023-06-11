package com.jmoreno.superpoderespractica.ui.navigation

sealed class Screens(val route: String) {
    object LoginScreen : Screens(SCREEN1_BASE_ROUTE)
    object SuperheroScreen : Screens(SCREEN2_BASE_ROUTE)

    companion object {
        private const val SCREEN1_BASE_ROUTE = "LoginScreen"
        private const val SCREEN2_BASE_ROUTE = "SuperheroScreen"

    }
}
