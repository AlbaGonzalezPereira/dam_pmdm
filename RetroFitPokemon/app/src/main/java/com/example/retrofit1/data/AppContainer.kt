package com.example.retrofit1.data

import android.content.Context
import com.example.retrofit1.network.PokemonRepository
import retrofit2.Retrofit


class AppContainer(context: Context) {
    /* room
    private val appDatabase = AppDatabase.getDatabase(context)
    private val discoDao = appDatabase.discoDao()
    private val discoRepository = DiscoRepositoryImpl(discoDao)

    fun provideDiscoRepository(): DiscoRepository = discoRepository
    */


    // Pokemon Repository -> retrofit
    val pokemonRepository: PokemonRepository by lazy {
        PokemonRepository()
    }
}