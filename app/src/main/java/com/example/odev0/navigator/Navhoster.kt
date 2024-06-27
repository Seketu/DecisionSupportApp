package com.example.odev0.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.odev0.Views.ChatView
import com.example.odev0.Views.EndView
import com.example.odev0.Views.MainView
import com.example.odev0.Views.SearchView
import kotlinx.serialization.Serializable

object Navhoster {
    @Composable
    fun Navigator() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = SignScreen) {
            composable<SignScreen> {
                MainView().mainView(navController)
            }
            composable<SearchScreen> {
                SearchView().searcView(navController)
            }
            composable<ChatScreen> {
                ChatView().chatView(navController = navController)
            }
            composable<EndScreen> {
                EndView().endView(navController = navController)
            }
        }
    }


    @Serializable
    object SignScreen

    @Serializable
    object SearchScreen

    @Serializable
    object ChatScreen

    @Serializable
    object EndScreen
}
