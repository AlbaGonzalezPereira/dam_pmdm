package com.example.examen2eval.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examen2eval.ui.AppViewModelProvider
import com.example.examen2eval.ui.navegacion.NavegacionDestino
import kotlinx.coroutines.launch

object AddDestino : NavegacionDestino {
    override val ruta = "add"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDiscoScreen(
    modifier: Modifier = Modifier,
    navegacionVolver: () -> Unit,
    viewModel: AddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope =
        rememberCoroutineScope() //para los métodos suspend (añadir, modificar y eliminar)
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = navegacionVolver) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                title = { Text("Añadir disco") })
        }
    )

    {
        val uiState = viewModel.addUiState
        val discoDetails = uiState.discoDetails
        val onValueChange = viewModel::updateUiState
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //creamos una variable disco para obtener los datos

            AddRow(
                label = "Título: ",
                value = discoDetails.titulo,
                onValueChange = { onValueChange(discoDetails.copy(titulo = it)) },
                numerico = false
            )
            AddRow(
                label = "Autor: ",
                value = discoDetails.autor,
                onValueChange = { onValueChange(discoDetails.copy(autor = it)) },
                numerico = false
            )
            AddRow(
                label = "Núm. canciones: ",
                value = discoDetails.numCanciones,
                onValueChange = { onValueChange(discoDetails.copy(numCanciones = it)) },
                numerico = true
            )
            AddRow(
                label = "Año publicación: ",
                value = discoDetails.publicacion,
                onValueChange = { onValueChange(discoDetails.copy(publicacion = it)) },
                numerico = true
            )
            AddRow(
                label = "Valoración: ",
                value = discoDetails.valoracion,
                onValueChange = { onValueChange(discoDetails.copy(valoracion = it)) },
                numerico = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.saveDisco()
                        }
                        navegacionVolver()
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Text(text = "Guardar")
                }

                Spacer(modifier = Modifier.width(30.dp))

                Button(onClick = navegacionVolver) {
                    Text(text = "Volver")
                }
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRow(label: String, value: String, onValueChange: (String) -> Unit, numerico: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(0.5f)) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold
            )
        }
        Box(modifier = Modifier.weight(0.5f)) {
            //ponemos el teclado numérico o el qwerty
            if (numerico) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            } else {
                OutlinedTextField(value = value, onValueChange = onValueChange)
            }

        }
    }
}