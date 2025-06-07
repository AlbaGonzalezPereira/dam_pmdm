package com.example.retrofit1.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.retrofit1.ui.screens.PrincipalDestino
import com.example.retrofit1.ui.screens.PrincipalScreen


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
               /* navegarDetalles = {
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

                */
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


    }
}
