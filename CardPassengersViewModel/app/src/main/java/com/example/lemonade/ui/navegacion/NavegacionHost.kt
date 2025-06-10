package com.example.lemonade.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lemonade.ui.screens.PrincipalDestino
import com.example.lemonade.ui.screens.PrincipalScreen
import com.example.lemonade.ui.screens.SecondDestino
import com.example.lemonade.ui.screens.SecondScreen
import com.example.lemonade.ui.screens.ThirdDestino
import com.example.lemonade.ui.screens.ThirdScreen

@Composable
fun NavegacionHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = PrincipalDestino.ruta,
    ) {
        composable(route = PrincipalDestino.ruta) {
            PrincipalScreen(
                navegarSecond = {
                    navController.navigate(SecondDestino.ruta)
                },
                navegarThird = {
                    navController.navigate(ThirdDestino.ruta)
                }
            )
        }
        composable(route = SecondDestino.ruta) {
            SecondScreen(
                //para volver se usa el método popBackStack()
                navegacionVolver = { navController.popBackStack() }
            )
        }

        composable(route = ThirdDestino.ruta) {
            ThirdScreen(
                //para volver se usa el método popBackStack()
                navegacionVolver = { navController.popBackStack() },
                navegacionPrincipal = {navController.navigate(PrincipalDestino.ruta)}
            )
        }
    }
}