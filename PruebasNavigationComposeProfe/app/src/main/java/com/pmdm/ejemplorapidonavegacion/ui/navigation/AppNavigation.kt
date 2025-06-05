package com.pmdm.ejemplorapidonavegacion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmdm.ejemplorapidonavegacion.ui.screens.FirstScreen
import com.pmdm.ejemplorapidonavegacion.ui.screens.FourthScreen
import com.pmdm.ejemplorapidonavegacion.ui.screens.SecondScreen
import com.pmdm.ejemplorapidonavegacion.ui.screens.ThirdScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Navigation
    NavHost(navController = navController, startDestination = AppScreens.FIRST_SCREEN.name) {
        composable(AppScreens.FIRST_SCREEN.name) {
            FirstScreen(
                navigateToSecondScreen = { navController.navigate(AppScreens.SECOND_SCREEN.name) },
                navigateToThirdScreen = { navController.navigate(AppScreens.THIRD_SCREEN.name) },
                navigateToFourthScreen = { navController.navigate(AppScreens.FOURTH_SCREEN.name) },
            )
        }
        composable(AppScreens.SECOND_SCREEN.name) {
            SecondScreen(
                navigateToThirdScreen = { navController.navigate(AppScreens.THIRD_SCREEN.name) },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(AppScreens.THIRD_SCREEN.name) {
            ThirdScreen(
                navigateToFirstScreen = { navController.popBackStack(AppScreens.FIRST_SCREEN.name, false) },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(AppScreens.FOURTH_SCREEN.name) {
            FourthScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

    }


}