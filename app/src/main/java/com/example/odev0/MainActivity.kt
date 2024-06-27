package com.example.odev0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.odev0.Views.MainView
import com.example.odev0.navigator.Navhoster
import com.example.odev0.ui.theme.Odev0Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Odev0Theme {
                Navhoster.Navigator()
            }
        }
    }
}

