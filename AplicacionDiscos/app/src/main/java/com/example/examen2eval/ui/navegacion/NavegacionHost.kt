package com.example.examen2eval.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.examen2eval.ui.screens.AddDestino
import com.example.examen2eval.ui.screens.AddDiscoScreen
import com.example.examen2eval.ui.screens.DetallesDestino
import com.example.examen2eval.ui.screens.DetallesScreen
import com.example.examen2eval.ui.screens.EditarDestino
import com.example.examen2eval.ui.screens.EditarScreen
import com.example.examen2eval.ui.screens.PrincipalDestino
import com.example.examen2eval.ui.screens.PrincipalScreen

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
                navegarDetalles = {
                    //navController.navigate(DetallesDestino.ruta)
                    navController.navigate("${DetallesDestino.ruta}/$it")//it vale el id del disco
                },
                navegarAdd = {
                    navController.navigate(AddDestino.ruta)
                },
                navegarEditar = {
                    //navController.navigate(DetallesDestino.ruta)
                    navController.navigate("${EditarDestino.ruta}/$it")//it vale el id del disco
                }
                /*     navigateToAddProduct = {
                         navController.navigate(ProductAddDestination.route)
                     },
                     navigateToProductDetails = {
                         navController.navigate("${ProductDetailsDestination.route}/$it")
                     },
                     navigateToProductUpdate = {
                         navController.navigate("${ProductUpdateDestination.route}/$it")
                     }*/
            )
        }
        composable(
            route = DetallesDestino.routeWithArgs,
            arguments = listOf(navArgument(DetallesDestino.detailIdArg) {
                type = NavType.IntType
            })
        ) {
            DetallesScreen(
                //para volver se usa el método popBackStack()
                navegacionVolver = { navController.popBackStack() },
                navegacionEditar = { navController.navigate("${EditarDestino.ruta}/$it") }
            )
        }

        composable(route = AddDestino.ruta) {
            AddDiscoScreen(
                //para volver se usa el método popBackStack()
                navegacionVolver = { navController.popBackStack() }
            )
        }
        composable(
            route = EditarDestino.routeWithArgs,
            arguments = listOf(navArgument(DetallesDestino.detailIdArg) {
                type = NavType.IntType
            })
        ) {
            EditarScreen(
                //para volver se usa el método popBackStack()
                navegacionVolver = { navController.popBackStack() }
            )
        }

    }
}
