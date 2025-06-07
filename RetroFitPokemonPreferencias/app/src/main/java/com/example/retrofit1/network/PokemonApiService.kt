package com.example.retrofit1.network

import com.example.retrofit1.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService { // en el repository ponemos la url principal
    @GET("pokemon") //https://pokeapi.co/api/v2/pokemon
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20, // https://pokeapi.co/api/v2/pokemon?limit=100
        @Query("offset") offset: Int = 0 //https://pokeapi.co/api/v2/pokemon?limit=100&offset=0
    ): PokemonResponse
}
