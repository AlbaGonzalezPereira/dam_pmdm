package com.example.simulacroexamen1ev.navigation

sealed class AppScreens(val ruta : String) {
    object ScreenHome : AppScreens("Home")
    object ScreenGame : AppScreens("Game")
    object ScreenGameOver : AppScreens("GameOver")
}