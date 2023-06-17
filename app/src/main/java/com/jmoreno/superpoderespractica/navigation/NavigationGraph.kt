package com.jmoreno.superpoderespractica.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jmoreno.superpoderespractica.ui.heroeDetail.DetailScreen
import com.jmoreno.superpoderespractica.ui.superherolist.SuperHeroListScreen
import com.jmoreno.superpoderespractica.ui.superherolist.SuperHeroListViewModel

@Composable
fun NavigationGraph(superHeroListViewModel: SuperHeroListViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SuperheroListScreen.route) {
        composable(Screens.SuperheroListScreen.route) {
            SuperHeroListScreen(viewModel = superHeroListViewModel) {
                navController.navigate(Screens.DetailScreen.createRouteWithArgs(it))
            }
        }

        composable(
            Screens.DetailScreen.route, arguments = listOf(
                navArgument(Screens.DetailScreen.ARG_ID){
                    this.type = NavType.LongType
                },

            )){

            val heroId = it.arguments?.getLong(Screens.DetailScreen.ARG_ID)

            if (heroId != null){
                Log.d("heroID","Entra con argumento")
                DetailScreen(heroId,superHeroListViewModel)
            } else {
                navController.navigateUp()
            }
        }
    }
}
