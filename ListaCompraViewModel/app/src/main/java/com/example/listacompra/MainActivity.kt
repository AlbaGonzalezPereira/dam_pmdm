package com.example.listacompra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.listacompra.ui.screens.ScreenPrincipal
import com.example.listacompra.ui.theme.ListaCompraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaCompraTheme {
                // A surface container using the 'background' color from the theme
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaPreview() {
    ListaCompraTheme {
        ScreenPrincipal()
    }
}