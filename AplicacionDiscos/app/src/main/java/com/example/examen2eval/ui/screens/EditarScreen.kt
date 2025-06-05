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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examen2eval.ui.AppViewModelProvider
import com.example.examen2eval.ui.navegacion.NavegacionDestino
import kotlinx.coroutines.launch

object EditarDestino : NavegacionDestino {
    override val ruta = "editar"
    const val detailIdArg = "detailId"
    val routeWithArgs = "${EditarDestino.ruta}/{$detailIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarScreen(
    modifier: Modifier = Modifier,
    navegacionVolver: () -> Unit,
    viewModel: EditarViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
                title = { Text("Editar disco " + viewModel.editarUiState.discoDetails.titulo) })
        }
    )

    {
        val uiState = viewModel.editarUiState
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

            EditarRow(
                label = "Título: ",
                value = discoDetails.titulo,
                onValueChange = { onValueChange(discoDetails.copy(titulo = it)) },
                numerico = false
            )
            EditarRow(
                label = "Autor: ",
                value = discoDetails.autor,
                onValueChange = { onValueChange(discoDetails.copy(autor = it)) },
                numerico = false
            )
            EditarRow(
                label = "Núm. canciones: ",
                value = discoDetails.numCanciones,
                onValueChange = { onValueChange(discoDetails.copy(numCanciones = it)) },
                numerico = true
            )
            EditarRow(
                label = "Año publicación: ",
                value = discoDetails.publicacion,
                onValueChange = { onValueChange(discoDetails.copy(publicacion = it)) },
                numerico = true
            )
            EditarRow(
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
                            viewModel.updateDisco()
                        }
                        navegacionVolver()
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Guardar cambios")
                }

                Spacer(modifier = Modifier.width(30.dp))

                Button(onClick = navegacionVolver, shape = RectangleShape) {
                    Text(text = "Volver")
                }
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarRow(label: String, value: String, onValueChange: (String) -> Unit, numerico: Boolean) {

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
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = if (numerico) KeyboardType.Number else KeyboardType.Text
                )

            )
        }
    }
}