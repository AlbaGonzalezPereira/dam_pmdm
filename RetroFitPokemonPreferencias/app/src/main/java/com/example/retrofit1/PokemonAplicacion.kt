package com.example.retrofit1

import android.app.Application

import com.example.retrofit1.data.AppContainer

class PokemonAplicacion : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        //inicializamos la base de datos y otras dependencias
        appContainer = AppContainer(this)
    }
}