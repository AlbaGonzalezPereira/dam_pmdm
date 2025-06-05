package com.example.examen2eval

import android.app.Application
import com.example.examen2eval.data.AppContainer

class DiscosAplicacion : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        //inicializamos la base de datos y otras dependencias
        appContainer = AppContainer(this)
    }
}