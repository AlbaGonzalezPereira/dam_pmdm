package com.pmdm.ejemplorapidonavegacion.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(
    navigateToThirdScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold (
        topBar =  {
            TopAppBar(
                title = { Text("Second Screen") },
                navigationIcon = {
                    Button(onClick = { navigateBack() }) {
                        Text("Back")
                    }
                }
            )
        }
    ) {
        SecondScreenContent(navigateToThirdScreen, it)
    }

}

@Composable
fun SecondScreenContent(
    navigateToThirdScreen: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(16.dp)
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Second Screen")
        Button(
            onClick = { navigateToThirdScreen() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Go to Third Screen")
        }
    }
}