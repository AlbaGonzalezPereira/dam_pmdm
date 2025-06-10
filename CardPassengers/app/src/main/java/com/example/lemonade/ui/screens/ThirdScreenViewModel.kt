package com.example.lemonade.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ThirdUiState(
    val texto: String = "0",

    )

class ThirdViewModel(
    /* private val discoRepository: DiscoRepository*/
) : ViewModel() {
    /*
    private val _principalUiState = MutableStateFlow(PrincipalUiState()) //para trabajar dentro de la clase
    val principalUiState: StateFlow<PrincipalUiState> = _principalUiState.asStateFlow()//para acceder desde otras clases
    */
    var thirdUiState by mutableStateOf(ThirdUiState())
        private set

    // Actualizamos todos los datos, sobreescribiendo discoDetails
    fun incrementarPassengers() {
        var textoInt = thirdUiState.texto.toInt()
        textoInt++
        thirdUiState = thirdUiState.copy(texto = textoInt.toString())
    }

    fun decrementarPassengers() {
        var textoInt = thirdUiState.texto.toInt()
        textoInt--
        if (textoInt < 0) {
            textoInt = 0
        }
        thirdUiState = thirdUiState.copy(texto = textoInt.toString())
    }

    init {

    }


}