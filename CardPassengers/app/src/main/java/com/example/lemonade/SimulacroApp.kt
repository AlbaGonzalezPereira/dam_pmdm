package com.example.lemonade

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lemonade.ui.navegacion.NavegacionHost

@Composable
fun SimulacroApp(navController: NavHostController = rememberNavController()) {
    NavegacionHost(navController)
}