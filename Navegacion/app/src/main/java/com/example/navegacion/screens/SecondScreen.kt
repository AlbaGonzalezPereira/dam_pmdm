package com.example.navegacion.screens

import android.widget.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.navegacion.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(texto: String?, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Segunda pantalla") },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    modifier = Modifier.clickable { navController.popBackStack()})
            }
        )
    }) {
        SecondBodyContent(
            modifier = Modifier.padding(it),
            texto,
            navController
        )

    }
}

@Composable
fun SecondBodyContent(modifier: Modifier, texto: String?, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("He navegado a la segunda pantalla")
        texto?.let { Text("$texto") }
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")

        }
        Button(onClick = { navController.navigate(AppScreens.ThirdScreen.route) }) {
            Text("Seguir")

        }

    }

}