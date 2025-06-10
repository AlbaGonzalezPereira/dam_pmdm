package com.example.lemonade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lemonade.ui.AppViewModelProvider
import com.example.lemonade.ui.navegacion.NavegacionDestino

object PrincipalDestino : NavegacionDestino {
    override val ruta = "principal"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    modifier: Modifier = Modifier,
    navegarSecond: () -> Unit,
    navegarThird: () -> Unit,
    /*habilitarBoton: (String) -> Unit,*/
    viewModel: PrincipalViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.principalUiState
    val onValueChange = viewModel::comprobarTextFieldUiState
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pantalla principal") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ) {
                Column(
                    modifier = modifier.fillMaxSize(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("By Alba González")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text("Buenas tardes!!!")
            TextField(value = uiState.texto, onValueChange = {onValueChange(it)})
            Button(onClick = navegarSecond, enabled = uiState.habilitarBoton) {
                Text(text = "Segunda!!")
            }

            Button(onClick = navegarThird) {
                Text(text = "Tercera!!")
            }
        }

    }

}


