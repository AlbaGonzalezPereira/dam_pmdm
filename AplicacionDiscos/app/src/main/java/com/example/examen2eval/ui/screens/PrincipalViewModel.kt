package com.example.examen2eval.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen2eval.data.Disco
import com.example.examen2eval.data.DiscoRepository
import com.example.examen2eval.data.startingDiscos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.math.pow

data class PrincipalUiState(
    val listaDiscos: List<Disco> = listOf(),
    val valoracionMedia: Double = 0.0,
    val showDeleteDialog: Boolean = false,
    val discoToDelete: Disco? = null
)

class PrincipalViewModel(
    private val discoRepository: DiscoRepository
) : ViewModel() {


    private val _principalUiState =
        MutableStateFlow(PrincipalUiState()) //para trabajar dentro de la clase
    val principalUiState: StateFlow<PrincipalUiState> =
        _principalUiState.asStateFlow()//para acceder desde otras clases

    init {
        viewModelScope.launch {
            discoRepository.getAll().map { discos ->
                var valoracionMedia = discos.map { it.valoracion.toDouble() }.average()
                // Limitamos los decimales de valoracionMedia a 2
                valoracionMedia = (valoracionMedia * 10.0.pow(2)).toLong() / 10.0.pow(2)
                PrincipalUiState(listaDiscos = discos, valoracionMedia = valoracionMedia)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = PrincipalUiState()
            ).collect {
                _principalUiState.value = it
            }
        }
    }

    fun insertarDiscos() {
        //Log.d("gfg","gddfgdf")
        viewModelScope.launch {
            for (discos in startingDiscos) { //startingDiscos está en DiscosData
                discoRepository.insert(discos)
            }
        }
    }

    fun borrarDisco(disco: Disco) {
        viewModelScope.launch {
            discoRepository.delete(disco)
        }
    }

    /**
     * para que se actualice cada 5 sg
     */
    companion object {
        private const val TIMEOUT_MILLIS = 5000L
    }
}