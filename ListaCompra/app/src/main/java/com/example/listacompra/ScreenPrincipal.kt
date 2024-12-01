package com.example.listacompra

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Producto(var realizado: Boolean = false, val nombreProducto: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenPrincipal() {
    //creamos una variable mutable ya que en el textfield vamos cambiando los elementos a añadir
    var nombre by remember { mutableStateOf("") }
    val nombres = remember { mutableStateListOf<Producto>() }
    Scaffold(topBar = {
        TopAppBar(title = { Text("Lista de la compra") },
            actions = {//borra los productos seleccionados
            OutlinedButton(
                onClick = {
                    nombres.removeAll { it.realizado }
                },
                enabled = nombres.any { !it.realizado } // Habilitado si hay al menos uno seleccionado
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
            TextField(value = nombre, onValueChange = {
                nombre = it //guardamos el nombre
            }, modifier = Modifier.fillMaxWidth(), label = {
                Text("Producto: ")
            })
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center // Centra el contenido dentro del Box
            ) {
                Button(onClick = {
                    if (nombre != "") {
                        nombres.add(Producto(nombreProducto = nombre))
                        nombre = ""
                    }
                }) {
                    Text(text = "Añadir")
                }
            }
            VerLista(listaNombres = nombres)
        }
    }
}

@Composable
fun VerLista(listaNombres: MutableList<Producto>) {
    //el LazyColumn trabaja con items
    LazyColumn() {
        item {
            Text("Lista de productos")

        }
        items(listaNombres) { producto ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                var checkeado by remember {
                    mutableStateOf(producto.realizado)
                }
                Checkbox(checked = checkeado, onCheckedChange = {
                    checkeado = it
                    producto.realizado = it
                })
                Text(producto.nombreProducto)
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    listaNombres.remove(producto)
                }) {
                    Text(text = "Borrar")
                }
                Text(producto.realizado.toString())
            }


        }

    }
}