package com.example.retrofit1

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.retrofit1.ui.navegacion.NavegacionHost

@Composable
fun DiscosApp(navController: NavHostController = rememberNavController()) {
    NavegacionHost(navController)
}