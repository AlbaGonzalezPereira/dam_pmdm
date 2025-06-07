package com.example.retrofit1.network

import com.example.retrofit1.model.PokemonResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(PokemonApiService::class.java)

    // creamos la función para obtener la lista de pokemons. LLama al método getPokemonList del Servicio
    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): PokemonResponse {
        return apiService.getPokemonList(limit, offset)
    }
}