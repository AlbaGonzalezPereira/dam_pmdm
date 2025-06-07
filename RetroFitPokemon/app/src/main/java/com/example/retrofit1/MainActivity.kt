package com.example.retrofit1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.retrofit1.ui.theme.RetroFit1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetroFit1Theme {
                // A surface container using the 'background' color from the theme
                DiscosApp()
            }
        }
    }
}
