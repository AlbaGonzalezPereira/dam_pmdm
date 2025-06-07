package com.example.retrofit1.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofit1.PokemonAplicacion
import com.example.retrofit1.ui.screens.PrincipalViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokemonAplicacion)
            val pokemonRepository = application.appContainer.pokemonRepository

            //en la pantalla principal cogemos el repositorio para acceder a la petición http
            PrincipalViewModel(pokemonRepository = pokemonRepository/*discosApp().appContainer.provideDiscoRepository()*/)
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

fun CreationExtras.pokemonApp(): PokemonAplicacion =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokemonAplicacion)