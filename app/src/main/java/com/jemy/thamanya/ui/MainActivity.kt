package com.jemy.thamanya.ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jemy.thamanya.ui.screens.Navigation
import com.jemy.thamanya.ui.screens.home.Home
import com.jemy.thamanya.ui.screens.search.Search
import com.jemy.thamanya.ui.ui.themes.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAppTheme {
                AppNavigation()
            }
        }
    }

    @Composable
    private fun AppNavigation() {
        val navController = rememberNavController();
        NavHost(navController, startDestination = Navigation.Home.name) {
            composable(Navigation.Home.name) { Home(navController) }
            composable(Navigation.Search.name) { Search(navController) }
        }
    }
}