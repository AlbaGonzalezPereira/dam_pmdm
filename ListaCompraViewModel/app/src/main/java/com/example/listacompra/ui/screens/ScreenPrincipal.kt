package com.example.listacompra.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listacompra.model.Producto
import com.example.listacompra.ui.state.ListaCompraEstado
import com.example.listacompra.ui.state.ListaCompraViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenPrincipal(listaViewModel : ListaCompraViewModel = viewModel()) {
    //creamos una variable mutable ya que en el textfield vamos cambiando los elementos a añadir
    //var nombre by remember { mutableStateOf("") }
    //val nombres = remember { mutableStateListOf<Producto>() }
    val estado by listaViewModel.uiState.collectAsState() // No se necesita gestionar manualmente las suscripciones o llamadas para actualizar la UI.
    Scaffold(topBar = {
        TopAppBar(title = { Text("Lista de la compra") },
            actions = {//borra los productos seleccionados
            OutlinedButton(
                onClick = {
                    //nombres.removeAll { it.realizado }
                },
                enabled = true // Habilitado si hay al menos uno seleccionado
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Borrar seleccionados")
            }
        })
    }) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Text(
                text = "Introduce un producto:",
                modifier = Modifier.padding(end = 8.dp) // Espacio entre la label y el TextField
            )
            TextField(value = estado.nombreProducto, onValueChange = {
                listaViewModel.cambiarNombreproducto(it) //recibe lo que tiene el TextField y lo guarda en el estado

            }, modifier = Modifier.fillMaxWidth(), label = {
                Text("Producto: ")
            })
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center // Centra el contenido dentro del Box
            ) {
                Button(onClick = {
                    listaViewModel.agregarProducto()
                }) {
                    Text(text = "Agregar")
                }
            }
            VerLista(listaNombres = listaViewModel.getListaProductos(), listaViewModel,estado)
        }
    }
}

@Composable
fun VerLista(listaNombres: List<Producto>, listaViewModel: ListaCompraViewModel,estado : ListaCompraEstado) {
    //el LazyColumn trabaja con items
    LazyColumn() {
        item {
            Text("Lista de productos")

        }
        items(listaNombres) { producto ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                //var checkeado by remember {
                //    mutableStateOf(producto.realizado)
                //}
                Checkbox(checked = estado.checkeado, onCheckedChange = {
                    //checkeado = it
                    //producto.realizado = it
                    listaViewModel.seleccionarProducto()
                })
                Text(producto.nombreProducto)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = if (estado.checkeado) "Realizado" else "No realizado",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Button(onClick = {
                    //listaNombres.remove(producto)
                    listaViewModel.borrarSeleccionados(producto)
                }) {
                    Text(text = "Borrar")
                }
                //Text(producto.realizado.toString()) //para que salga el valor (true o false)

            }

        }

    }
}