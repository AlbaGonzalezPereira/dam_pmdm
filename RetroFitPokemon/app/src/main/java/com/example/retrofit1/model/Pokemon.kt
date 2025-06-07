package com.example.retrofit1.model

import kotlinx.serialization.Serializable

data class Pokemon(
    val name: String,
    val url: String
)


data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

/*https://pokeapi.co/api/v2/pokemon?limit=100&offset=0*/