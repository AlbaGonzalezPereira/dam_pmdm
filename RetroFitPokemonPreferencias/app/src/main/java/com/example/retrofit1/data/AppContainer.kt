package com.example.retrofit1.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

import com.example.retrofit1.network.PokemonRepository
import com.example.retrofit1.network.PreferenciasRepository
import retrofit2.Retrofit

// Extension property to create DataStore
private const val DATA_STORE_NAME = "pokemon_preferencias"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

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

    // Preferences Repository
    val preferenciasRepository: PreferenciasRepository by lazy {
        PreferenciasRepository(context.dataStore)
    }
}