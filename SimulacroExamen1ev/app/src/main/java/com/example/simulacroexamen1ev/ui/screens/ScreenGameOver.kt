package com.example.simulacroexamen1ev.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simulacroexamen1ev.navigation.AppScreens

@Composable
fun ScreenGameOver(text: String?, navController: NavController) {
    Column(
        modifier = Modifier
            .padding()
            .fillMaxSize(), // Ocupa todo el espacio disponible
        verticalArrangement = Arrangement.Center, // Centra el contenido verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        if(text == "Empate"){
            Text(
                text = "Ha habido un empate",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        else{
            Text(
                text = "Ha ganado el $text",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = {
            navController.popBackStack(AppScreens.ScreenHome.ruta, false) //para borrar la pila y vaya al home
        }) {
            Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Refrescar")
            Text("Reiniciar")

        }

    }
}