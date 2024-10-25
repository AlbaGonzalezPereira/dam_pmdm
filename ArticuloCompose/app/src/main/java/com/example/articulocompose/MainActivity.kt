package com.example.articulocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.articulocompose.ui.theme.ArticuloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticuloComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column() {

                        Row {
                            val imagen = painterResource(R.drawable.bg_compose_background)
                            Image(
                                imagen,
                                contentDescription = null
                            )
                        }

                        Row(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = getString(R.string.texto1),
                                fontSize = 24.sp

                            )
                        }

                        Row(modifier = Modifier.padding(16.dp, 16.dp)) {
                            Text(
                                text = getString(R.string.texto2),
                                textAlign = TextAlign.Justify
                            )
                        }

                        Row(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = getString(R.string.texto3),
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArticuloComposeTheme {
        Greeting("Android")
    }
}