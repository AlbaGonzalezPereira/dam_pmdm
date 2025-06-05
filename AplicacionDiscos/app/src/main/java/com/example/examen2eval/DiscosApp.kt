package com.example.examen2eval

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.examen2eval.ui.navegacion.NavegacionHost

@Composable
fun DiscosApp(navController: NavHostController = rememberNavController()) {
    NavegacionHost(navController)
}