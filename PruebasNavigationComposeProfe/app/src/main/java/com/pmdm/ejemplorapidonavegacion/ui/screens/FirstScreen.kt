package com.pmdm.ejemplorapidonavegacion.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FirstScreen(
    navigateToSecondScreen: () -> Unit,
    navigateToThirdScreen: () -> Unit,
    navigateToFourthScreen: () -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("First Screen")
        Button(
            onClick = { navigateToSecondScreen() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Go to Second Screen")
        }
        Button(
            onClick = { navigateToThirdScreen() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Go to Third Screen")
        }
        Button(
            onClick = { navigateToFourthScreen() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Go to Fourth Screen")
        }
    }
}