package com.example.examen2eval.ui.screens

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen2eval.data.DiscoRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

data class EditarUiState(
    val discoDetails: DiscoDetails = DiscoDetails()
    /*val isSaveButtonEnabled: Boolean = false //mientras no introduzca todos los datos no puede guardar*/
)

class EditarViewModel(
    savedStateHandle: SavedStateHandle, //para guardar lo que se le pasa por la url
    private val discoRepository: DiscoRepository
) : ViewModel() {
    companion object {
        private const val DETAIL_ID = "detailId"
    }

    var editarUiState by mutableStateOf(EditarUiState())
        private set

    // Actualizamos todos los datos, sobreescribiendo discoDetails
    fun updateUiState(discoDetails: DiscoDetails) {
        editarUiState = editarUiState.copy(
            discoDetails = discoDetails
            //isSaveButtonEnabled = validateInput(discoDetails)
        )
    }

    private val discoId: Int = checkNotNull(savedStateHandle[EditarDestino.detailIdArg])

    init {
        //Log.d("DetailViewModel", "discoId: $discoId")
        Log.d("EditarViewModel", "discoId: $discoId")
        val discoDetails = discoRepository.getDisco(discoId).filterNotNull().map {
            it.toDiscoDetails()
        }
        viewModelScope.launch {
            discoDetails.collect {
                updateUiState(it)
            }
        }
    }

    //petición asíncrona a la base de datos con "suspend"
    suspend fun updateDisco(): Boolean {
        try {
            discoRepository.update(editarUiState.discoDetails.toDisco())
            return true
        } catch (e: SQLiteConstraintException) {
            return false
        }
        return true
    }
}