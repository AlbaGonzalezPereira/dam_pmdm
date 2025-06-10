package com.example.lemonade.ui

import com.example.lemonade.ui.screens.PrincipalViewModel



import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lemonade.SimulacroAplicacion


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            //en la pantalla principal cogemos el repositorio para acceder a la base de datos
            PrincipalViewModel()
        }
        /*
        initializer {
            DetallesViewModel(
                this.createSavedStateHandle(),
                discosApp().appContainer.provideDiscoRepository()
            )
        }
        initializer {
            AddViewModel(discosApp().appContainer.provideDiscoRepository())
        }
        initializer {
            EditarViewModel(
                this.createSavedStateHandle(),
                discosApp().appContainer.provideDiscoRepository()
            )
        }*/
        /*
        initializer {
            AddViewModel(discosApp().appContainer.provideDiscoRepository())
        }
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                discosApp().appContainer.provideDiscoRepository()
            )
        }*/
    }
}

fun CreationExtras.simulacroApp(): SimulacroAplicacion =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SimulacroAplicacion)