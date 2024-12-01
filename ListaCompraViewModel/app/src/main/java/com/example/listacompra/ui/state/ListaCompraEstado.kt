package com.example.listacompra.ui.state

import com.example.listacompra.model.Producto

// clase donde se va a almacenar el estado de la aplicación
data class ListaCompraEstado(
    val checkeado: Boolean = false,
    val nombreProducto: String = "",
    val listaProductos: List<Producto> = emptyList()
)