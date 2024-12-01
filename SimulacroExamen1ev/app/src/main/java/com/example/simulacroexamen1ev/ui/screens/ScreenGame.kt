package com.example.simulacroexamen1ev.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.simulacroexamen1ev.navigation.AppScreens
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenGame(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxWidth(), topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = {
                // Acción al presionar la flecha (ej. navegar hacia atrás)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, // Usa un ícono predefinido de Material Icons
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onPrimary // Color del icono
                )
            }
        }, title = { Text("Carta Alta") },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary, // Fondo de la barra
                titleContentColor = MaterialTheme.colorScheme.onPrimary, // Color del título
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // Color del ícono de navegación
            )
        )
    }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), // Ocupa todo el espacio disponible
            verticalArrangement = Arrangement.Center, // Centra el contenido verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
        ) {
            var randomNumber1 by remember { mutableStateOf(0) }
            var randomNumber2 by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {
                    randomNumber1 = Random.nextInt(1, 13)
                    // Genera el número aleatorio
                }, enabled = randomNumber1 == 0) {
                    Text("Área de juego de Jugador 1")
                }
                if (randomNumber1 != 0)
                    Text("Su carta es: " + randomNumber1.toString())
                else
                    Text("No ha robado aún")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {
                    randomNumber2 = Random.nextInt(1, 13)
                    // Genera el número aleatorio
                }, enabled = randomNumber2 == 0) {
                    Text("Área de juego de Jugador 2")
                }
                if (randomNumber2 != 0)
                    Text("Su carta es: " + randomNumber2.toString())
                else
                    Text("No ha robado aún")
            }
            Row {
                Button(onClick = {
                    if (randomNumber1 > randomNumber2) {
                        navController.navigate(AppScreens.ScreenGameOver.ruta + "/Jugador 1")
                    } else if (randomNumber1 < randomNumber2) {
                        navController.navigate(AppScreens.ScreenGameOver.ruta + "/Jugador 2")
                    } else {
                        navController.navigate(AppScreens.ScreenGameOver.ruta + "/Empate")
                    }
                }, enabled = (randomNumber1 != 0 && randomNumber2 != 0)) {
                    Text(text = "Terminar partida")

                }
            }

        }

    }
}