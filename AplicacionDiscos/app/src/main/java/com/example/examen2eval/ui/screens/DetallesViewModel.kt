package com.example.examen2eval.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen2eval.data.Disco
import com.example.examen2eval.data.DiscoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetallesViewModel(
    savedStateHandle: SavedStateHandle, //para guardar lo que se le pasa por la url
    private val discoRepository: DiscoRepository
) : ViewModel() {

    companion object {
        private const val DETAIL_ID = "detailId"
    }

    var discoDetalleUiState by mutableStateOf(DetalleUiState())
        private set

    private val discoId: Int = checkNotNull(savedStateHandle[DetallesDestino.detailIdArg])

    init {
        //Log.d("DetailViewModel", "discoId: $discoId")
        Log.d("DetailViewModel", "discoId: $discoId")
        val discoDetails = discoRepository.getDisco(discoId).filterNotNull().map {
            it.toDiscoDetails()
        }
        viewModelScope.launch {
            discoDetails.collect {
                updateUiState(it)
            }
        }


    }

    private fun updateUiState(details: DiscoDetails) {
        discoDetalleUiState = DetalleUiState(details)
    }
}

data class DetalleUiState(
    val discoDetails: DiscoDetails = DiscoDetails(),
)

/*****************************DISCODETAILS********************************************/
data class DiscoDetails(
    val id: Int = 0,
    val titulo: String = "",
    val autor: String = "",
    val numCanciones: String = "",
    val publicacion: String = "",
    var valoracion: String = "1",
)

fun Disco.toDiscoDetails(): DiscoDetails {
    return DiscoDetails(
        id = id,
        titulo = titulo,
        autor = autor,
        numCanciones = numCanciones.toString(),
        publicacion = publicacion.toString(),
        valoracion = valoracion.toString()
    )
}

fun DiscoDetails.toDisco(): Disco {
    return Disco(
        id = id,
        titulo = titulo,
        autor = autor,
        numCanciones = numCanciones.toInt(),
        publicacion = publicacion.toInt(),
        valoracion = valoracion.toInt()
    )
}
