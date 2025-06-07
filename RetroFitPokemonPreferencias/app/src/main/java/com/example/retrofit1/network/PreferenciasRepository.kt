package com.example.retrofit1.network

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenciasRepository(private val dataStore: DataStore<Preferences>)
{
    private companion object {
        val NUM_POK_KEY = intPreferencesKey("numPok")
    }

    val numPokFlow: Flow<Int> = dataStore.data.map { preferences ->
        preferences[NUM_POK_KEY] ?: 2
    }

    suspend fun writePokemonPreferences(num_pokemon: Int) {
        dataStore.edit { preferences ->
            preferences[NUM_POK_KEY] = num_pokemon
        }
    }
}