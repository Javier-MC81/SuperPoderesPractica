package com.jmoreno.superpoderespractica.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jmoreno.superpoderespractica.ui.superherolist.SuperHeroListScreen
import com.jmoreno.superpoderespractica.ui.superherolist.SuperHeroListViewModel
import com.jmoreno.superpoderespractica.ui.ui.login.LoginScreen
import com.jmoreno.superpoderespractica.ui.ui.login.LoginViewModel

@Composable
fun NavigationGraph(loginViewModel: LoginViewModel, superHeroListViewModel: SuperHeroListViewModel) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.LoginScreen.route) {
        composable(Screens.LoginScreen.route) {
            LoginScreen(viewModel = loginViewModel) {
                navController.navigate(Screens.SuperheroScreen.route)
            }
        }

        composable(Screens.SuperheroScreen.route) {
            SuperHeroListScreen(viewModel = superHeroListViewModel)
        }
    }
}
