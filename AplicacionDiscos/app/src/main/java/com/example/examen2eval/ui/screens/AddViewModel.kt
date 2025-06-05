package com.example.examen2eval.ui.screens

import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.examen2eval.data.DiscoRepository

data class AddUiState(
    val discoDetails: DiscoDetails = DiscoDetails(),
    /*val isSaveButtonEnabled: Boolean = false //mientras no introduzca todos los datos no puede guardar*/
)

class AddViewModel(
    private val discoRepository: DiscoRepository
) : ViewModel() {
    var addUiState by mutableStateOf(AddUiState())
        private set

    // Actualizamos todos los datos, sobreescribiendo discoDetails
    fun updateUiState(discoDetails: DiscoDetails) {
        addUiState = addUiState.copy(
            discoDetails = discoDetails
            //isSaveButtonEnabled = validateInput(discoDetails)
        )
    }

    //petición asíncrona a la base de datos con "suspend"
    suspend fun saveDisco(): Boolean {
        try {
            discoRepository.insert(addUiState.discoDetails.toDisco())
            return true
        } catch (e: SQLiteConstraintException) {
            return false
        }
        return true
    }
}