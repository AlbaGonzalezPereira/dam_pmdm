package com.example.examen2eval.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.examen2eval.DiscosAplicacion
import com.example.examen2eval.ui.screens.AddViewModel
import com.example.examen2eval.ui.screens.DetallesViewModel
import com.example.examen2eval.ui.screens.EditarViewModel
import com.example.examen2eval.ui.screens.PrincipalViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            //en la pantalla principal cogemos el repositorio para acceder a la base de datos
            PrincipalViewModel(discosApp().appContainer.provideDiscoRepository())
        }
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
        }
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

fun CreationExtras.discosApp(): DiscosAplicacion =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DiscosAplicacion)