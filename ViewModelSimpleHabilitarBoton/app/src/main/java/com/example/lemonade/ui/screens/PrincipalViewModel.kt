package com.example.lemonade.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PrincipalUiState(
    val texto: String = "",
    val habilitarBoton: Boolean = false,
)

class PrincipalViewModel(
   /* private val discoRepository: DiscoRepository*/
) : ViewModel() {
    /*
    private val _principalUiState = MutableStateFlow(PrincipalUiState()) //para trabajar dentro de la clase
    val principalUiState: StateFlow<PrincipalUiState> = _principalUiState.asStateFlow()//para acceder desde otras clases
    */
    var principalUiState by mutableStateOf(PrincipalUiState())
        private set

    // Actualizamos todos los datos, sobreescribiendo discoDetails
    fun comprobarTextFieldUiState(textoTextField: String) {
        if(!textoTextField.trim().equals("")){
            principalUiState = principalUiState.copy(
                habilitarBoton = true
            )
        } else {
            principalUiState = principalUiState.copy(
                habilitarBoton = false
            )
        }
        principalUiState = principalUiState.copy(
            texto = textoTextField
        )
    }
    init {

    }


}