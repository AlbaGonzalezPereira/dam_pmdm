package com.example.listacompra.ui.state

import androidx.lifecycle.ViewModel
import com.example.listacompra.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListaCompraViewModel : ViewModel() {
    private val _uiState =
        MutableStateFlow(ListaCompraEstado()) //es privado porque queremos poder acceder al estado desde el propio ViewModel

    //para poder acceder el estado, sólo para leerlo:
    val uiState: StateFlow<ListaCompraEstado> =
        _uiState.asStateFlow()//mira si hay algún cambio y si lo hay renderiza la página

    //función que recibe lo que tiene el TextField y lo guarda en el estado
    fun cambiarNombreproducto(it: String) {
        _uiState.value = _uiState.value.copy(nombreProducto = it)

    }

    //función que agrega un producto a la lista de productos
    fun agregarProducto() {
        //comprobamos que el textfield no esté vacío y si no lo está agregará el nombre
        if (_uiState.value.nombreProducto.isNotEmpty()) {
            _uiState.value = _uiState.value.copy(
                listaProductos = _uiState.value.listaProductos + Producto(nombreProducto = _uiState.value.nombreProducto),
                nombreProducto = ""
            )
        }
    }

    //función que nos devuelve la lista de todos los productos
    fun getListaProductos(): List<Producto> {
        return _uiState.value.listaProductos
    }

    fun borrarSeleccionados(producto: Producto) {
        _uiState.value = _uiState.value.copy(
            listaProductos = _uiState.value.listaProductos.filter { it != producto }
        )

    }

    //método que selecciona o deselecciona un producto, es decir, cambia el estado
    fun seleccionarProducto() {
        _uiState.value = _uiState.value.copy(
            checkeado = !_uiState.value.checkeado
        )
    }

    }


