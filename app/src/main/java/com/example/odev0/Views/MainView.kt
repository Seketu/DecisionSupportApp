package com.example.odev0.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.odev0.navigator.Navhoster

class MainView {
    @Composable
    fun mainView(navController: NavHostController) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Merhaba Kararlarında her zaman yanında olacak uygulamana Hoş geldin",
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontSize = 24.sp
                )

                Button(onClick = {
                    navController.navigate(Navhoster.SearchScreen)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray
                    )
                    ) {
                    Text(text = "Devam edelim")
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun preview(){
        mainView(rememberNavController())
    }
}