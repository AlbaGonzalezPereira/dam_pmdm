package com.example.lemonade

import android.app.Application
import com.example.lemonade.data.AppContainer

class SimulacroAplicacion : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        //inicializamos la base de datos y otras dependencias
        appContainer = AppContainer(this)
    }
}