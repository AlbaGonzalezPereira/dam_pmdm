package com.example.retrofit1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit1.model.Pokemon
import com.example.retrofit1.ui.AppViewModelProvider
import com.example.retrofit1.ui.navegacion.NavegacionDestino
import kotlinx.coroutines.launch
import retrofit2.http.GET

object PrincipalDestino : NavegacionDestino {
    override val ruta = "principal"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    modifier: Modifier = Modifier,
    viewModel: PrincipalViewModel = viewModel(factory = AppViewModelProvider.Factory)

) {
    val state = viewModel.principalUiState //accedemos a los datos y métodos del estado
    /*val coroutineScope =
        rememberCoroutineScope() //para los métodos suspend (añadir, modificar y eliminar)*/
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier= Modifier.fillMaxWidth(1f)
    ) {
        Row {
            Box(modifier= Modifier
                .weight(0.4f)
                .padding(10.dp)) {
                OutlinedTextField(
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text(text = "Número pokemon")},
                    value = state.numPokemon, 
                    onValueChange = {viewModel.setNumPokemon(it)})//le pasamos en el value el numero de pokemon
            }

            Spacer(modifier = Modifier.weight(0.1f))

            Box(modifier= Modifier
                .weight(0.5f)
                .padding(10.dp)) {
                Button(
                    onClick = {viewModel.guardarPreferencias()},
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    shape = RectangleShape) {
                    Text(text = "Guardar preferencias")
                }
            }

        }
        Row {
            Button(
                onClick = {viewModel.getPokemonList()},
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary)
            ) {
                Text(text = "Enseñar pokemon")
            }

            Spacer(modifier = Modifier.width(15.dp))
    
            Button(onClick = {viewModel.deletePokemonList()}) {
                Text(text = "Borrar pokemon")
            }
        }
        
        LazyColumn(
            modifier = modifier
        ) {
            items(state.listaPokemon) {
                 //   pokemon ->
                //Text(text = pokemon.name)
                RowPokemon(pokemon = it)
            }
        }
    }

}

@Composable
fun RowPokemon(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(8.dp),
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
                Text(text = pokemon.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
