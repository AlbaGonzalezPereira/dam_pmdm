package com.example.simulacroexamen1ev.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.simulacroexamen1ev.ui.screens.ScreenGame
import com.example.simulacroexamen1ev.ui.screens.ScreenGameOver
import com.example.simulacroexamen1ev.ui.screens.ScreenHome

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.ScreenHome.ruta) {
        composable(AppScreens.ScreenHome.ruta) { ScreenHome(navController) }
        composable(AppScreens.ScreenGame.ruta) { ScreenGame(navController) }
        composable(
            AppScreens.ScreenGameOver.ruta + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) { ScreenGameOver(it.arguments?.getString("text"), navController) }

    }
}