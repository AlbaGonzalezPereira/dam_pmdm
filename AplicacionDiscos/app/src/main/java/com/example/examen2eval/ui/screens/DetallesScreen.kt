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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examen2eval.ui.AppViewModelProvider
import com.example.examen2eval.ui.navegacion.NavegacionDestino

object DetallesDestino : NavegacionDestino {
    override val ruta = "detalles"
    const val detailIdArg = "detailId"
    val routeWithArgs = "$ruta/{$detailIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallesScreen(
    modifier: Modifier = Modifier,
    navegacionVolver: () -> Unit,
    navegacionEditar: (Int) -> Unit,
    viewModel: DetallesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
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
                title = { Text("Detalles del disco " + viewModel.discoDetalleUiState.discoDetails.titulo) })
        }
    )

    {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            /*
            Text(text = "Título: ")
            Text(text = "Autor: ")
            Text(text = "Número de canciones: ")
            Text(text = "Año de publicación: ")
            Text(text = "Valoración: ")
            */

            //creamos una variable disco para obtener los datos
            val disco = viewModel.discoDetalleUiState.discoDetails
            InfoRow(label = "Título: ", value = disco.titulo)
            InfoRow(label = "Autor: ", value = disco.autor)
            InfoRow(label = "Núm. canciones: ", value = disco.numCanciones)
            InfoRow(label = "Año publicación: ", value = disco.publicacion)
            InfoRow(label = "Valoración: ", value = disco.valoracion)
            Row {
                for (i in 1..5) {
                    Icon(
                        imageVector = if (i <= disco.valoracion.toInt()) Icons.Filled.Star else Icons.TwoTone.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Button(onClick = navegacionVolver) {
                    Text(text = "Volver")
                }

                Spacer(modifier = Modifier.width(30.dp))

                Button(onClick = { navegacionEditar(disco.id) }) {
                    Text(text = "Editar")
                }
            }

        }
    }

}

@Composable
fun InfoRow(label: String, value: String) {
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
            Text(
                text = value,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

/*
@Composable
@Preview(showSystemUi = true)
fun DetallesScreenPreview(){
DetallesScreen(modifier = Modifier, navegacionVolver = { } )
}
*/
