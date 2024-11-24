package com.example.navegacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navegacion.screens.FirstScreen
import com.example.navegacion.screens.SecondScreen
import com.example.navegacion.screens.ThirdScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
        composable(AppScreens.FirstScreen.route) { FirstScreen(navController) }
        composable(
            AppScreens.SecondScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) { SecondScreen(it.arguments?.getString("text"), navController) }
        composable(AppScreens.ThirdScreen.route) {ThirdScreen(navController)  }
    }
}