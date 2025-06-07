package com.example.retrofit1.ui.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit1.model.Pokemon
import com.example.retrofit1.network.PokemonRepository
import com.example.retrofit1.network.PreferenciasRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


data class PrincipalUiState(
    /*
    val listaDiscos: List<Disco> = listOf(),
    val valoracionMedia: Double = 0.0,
    val showDeleteDialog: Boolean = false,
    val discoToDelete: Disco? = null

     */

    val listaPokemon: List<Pokemon> = listOf(),
    val numPokemon: String = "20"
)

class PrincipalViewModel(
    //private val discoRepository: DiscoRepository
    private val pokemonRepository: PokemonRepository,
    private val preferenciasRepository: PreferenciasRepository
) : ViewModel() {
    //para acceder al estado
    var principalUiState by mutableStateOf(PrincipalUiState())
        private set

    init {
        viewModelScope.launch {
            preferenciasRepository.numPokFlow.collectLatest {
                principalUiState = principalUiState.copy(numPokemon = it.toString())
            }

        }
    }

    //función que carga los pokemon
    /*fun cargarPokemon(){
        viewModelScope.launch {
            val lista: List<Pokemon>
            lista = pokemonRepository.getPokemonList(10, 0).results
            principalUiState = principalUiState.copy(listaPokemon = lista)
        }
    }*/
    fun getPokemonList() {
        viewModelScope.launch {

            try {
                val response =
                    pokemonRepository.getPokemonList(principalUiState.numPokemon.toInt(), 0)
                principalUiState = principalUiState.copy(
                    listaPokemon = response.results,

                    )
            } catch (e: Exception) {
                /*principalUiState = principalUiState.copy(

                )*/
                println("Error al obtener pokémon: ${e.message}")

            }
        }
    }

    fun deletePokemonList() {
        principalUiState = principalUiState.copy(listaPokemon = emptyList())
    }

    fun setNumPokemon(nuevoNumPokemon: String) {
        principalUiState = principalUiState.copy(numPokemon = nuevoNumPokemon)

    }

    fun guardarPreferencias() {
        viewModelScope.launch {//porque la función es suspend
            preferenciasRepository.writePokemonPreferences(principalUiState.numPokemon.toInt())
        }
    }
}