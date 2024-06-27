package com.example.odev0.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class EndView {
    @Composable
    fun endView(modifier: Modifier = Modifier,navController: NavController) {
        Scaffold(
            Modifier.fillMaxSize()
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Umarım Sana Yardımcı Olmuşumdur, Kendine iyi bak")
                Spacer(modifier = Modifier.fillMaxHeight(0.3f))
                Text(text = "Uygulamayı en iyi bildiğin şekilde kapatabilirsin")
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun preview(){
        endView(navController = rememberNavController())
    }
}