package com.example.examen2eval.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examen2eval.data.Disco
import com.example.examen2eval.ui.AppViewModelProvider
import com.example.examen2eval.ui.navegacion.NavegacionDestino

object PrincipalDestino : NavegacionDestino {
    override val ruta = "principal"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    modifier: Modifier = Modifier,
    navegarDetalles: (Int) -> Unit, //selecciona el id del disco
    navegarAdd: () -> Unit,
    navegarEditar: (Int) -> Unit, //selecciona el id del disco
    viewModel: PrincipalViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val state by viewModel.principalUiState.collectAsState() //accedemos a los datos y métodos del estado
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de discos") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            // FloatingActionButton para añadir un producto
            FloatingActionButton(
                onClick = navegarAdd,
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = MaterialTheme.colorScheme.onSecondary,
                    contentDescription = "Añadir producto"
                )
            }
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
                    if (state.listaDiscos.isEmpty()) {
                        Text("No hay discos")
                    } else {
                        Text("Valoración: ${state.valoracionMedia}")
                    }
                }
            }
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
            /*Button(onClick = navegarDetalles){
                Text(text = "Ir a página 2")
            }*/
            if (state.listaDiscos.isEmpty()) {
                Text(text = "No hay discos", fontSize = 20.sp)
                Icon(
                    modifier = modifier.size(60.dp),
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "X",
                    tint = MaterialTheme.colorScheme.error
                )
                Button(onClick = { viewModel.insertarDiscos() }) {
                    Text(text = "Insertar")
                }

            } else {
                LazyColumn(
                    modifier = modifier
                ) {
                    items(state.listaDiscos) {
                        RowDiscos(disco = it, borrarDisco = { viewModel.borrarDisco(it) }, navegarDetalles = navegarDetalles, navegarEditar = navegarEditar)
                    }
                }
                //state.listaDiscos.forEach { RowDiscos() }
            }
        }
    }
}

@Composable
fun RowDiscos(disco: Disco, modifier: Modifier = Modifier, borrarDisco: () -> Unit,
              navegarDetalles: (Int) -> Unit, navegarEditar: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(8.dp)
            .clickable { navegarDetalles(disco.id) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier
                .fillMaxWidth(1f)
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(3f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = disco.titulo, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = disco.autor)
            }
            Spacer(Modifier.weight(1f))
            Row {
                for (i in 1..5) {
                    Icon(
                        imageVector = if (i <= disco.valoracion) Icons.Filled.Star else Icons.TwoTone.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
            Spacer(Modifier.weight(0.2f))
            Row {
                IconButton(onClick = { navegarEditar(disco.id) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        /*tint = MaterialTheme.colorScheme.primary*/
                    )
                }
                IconButton(onClick = borrarDisco) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        /*tint = MaterialTheme.colorScheme.primary*/
                    )
                }
                /*
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Información",
                        /*tint = MaterialTheme.colorScheme.primary*/
                    )
                }*/

            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun PrincipalScreenPreview() {
    PrincipalScreen(modifier = Modifier, { }, { }, { })
}

@Preview(showBackground = true)
@Composable
fun RowDiscosPreview() {
    RowDiscos(disco = Disco(0, "Disco", "Autor", 0, 0, 0), borrarDisco = {}, navegarDetalles = {}, navegarEditar = {})
}