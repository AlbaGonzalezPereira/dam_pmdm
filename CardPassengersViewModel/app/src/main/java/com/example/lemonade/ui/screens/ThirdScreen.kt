package com.example.lemonade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lemonade.ui.AppViewModelProvider
import com.example.lemonade.ui.navegacion.NavegacionDestino

object ThirdDestino : NavegacionDestino {
    override val ruta = "third"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(
    modifier: Modifier = Modifier,
    navegacionVolver: () -> Unit,
    navegacionPrincipal: () -> Unit,
    viewModel: ThirdViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.thirdUiState
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pantalla third") },
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
                    /*Text("By Alba González")*/
                    Row(
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = navegacionPrincipal, enabled = false) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                modifier = Modifier.size(50.dp),
                                contentDescription = "principal"
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { }, enabled = true) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = "Localizacion",
                                modifier = Modifier.size(50.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(onClick = { }, enabled = false) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Ajustes",
                                modifier = Modifier.size(50.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(onClick = { }, enabled = false) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                modifier = Modifier.size(50.dp),
                                contentDescription = "Persona"
                            )
                        }
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Finish!!!")
            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                colors = CardDefaults.cardColors(containerColor = Color.Gray)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)

                ) {
                    Row {

                        Column {
                            Row {
                                Text(text = "PASSENGER")

                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = { viewModel.decrementarPassengers()}) {
                                    Icon(
                                        imageVector = Icons.Filled.KeyboardArrowDown,
                                        tint = Color.Green,
                                        contentDescription = "Más"
                                    )
                                }
                                Text(
                                    text = uiState.texto,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Magenta
                                )

                                IconButton(onClick = { viewModel.incrementarPassengers() }) {
                                    Icon(
                                        imageVector = Icons.Filled.KeyboardArrowUp,
                                        tint = Color.Green,
                                        contentDescription = "Más"
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column {
                            Row {
                                Text(text = "TYPE")
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "TYPE",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Magenta
                                )
                            }
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Start) {
                        Column {
                            Text(text = "DEPART")
                            Text(
                                text = "Sun 3 Jun 2021",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Magenta
                            )
                        }

                    }
                }

            }
            Row {

                Button(onClick = navegacionVolver) {
                    Text(text = "Voltamos atrás!!")
                }

                Button(onClick = navegacionPrincipal) {
                    Text(text = "Voltamos principal!!")
                }
            }

        }

    }
}