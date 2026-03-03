package com.jemy.thamanya.ui.ui

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.jemy.thamanya.ui.screens.Navigation
import com.jemy.thamanya.ui.ui.screens.SharedViewModel
import com.jemy.thamanya.ui.ui.screens.aboutus.AboutUs
import com.jemy.thamanya.ui.ui.screens.activityscreen.ActivityScreen
import com.jemy.thamanya.ui.ui.screens.contactus.ContactUs
import com.jemy.thamanya.ui.ui.screens.home.Home
import com.jemy.thamanya.ui.ui.screens.news.News
import com.jemy.thamanya.ui.ui.screens.news.newsdetails.NewsDetails
import com.jemy.thamanya.ui.ui.screens.search.Search
import com.jemy.thamanya.ui.ui.screens.search.SearchResult
import com.jemy.thamanya.ui.ui.screens.subcategory.SubCategory
import com.jemy.thamanya.ui.ui.screens.surahs.Surahs
import com.jemy.thamanya.ui.ui.screens.surahs.surahdetails.SurahDetails
import com.jemy.thamanya.ui.ui.themes.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
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
        val sharedViewModel = hiltViewModel<SharedViewModel>()
        NavHost(navController, startDestination = Navigation.Home.name) {
            composable(Navigation.Home.name) { Home(navController, sharedViewModel) }
            composable(Navigation.Search.name) { Search(navController, sharedViewModel) }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val locale = Locale("ar")
        val config = Configuration(newBase.resources.configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)
        val context = newBase.createConfigurationContext(config)
        super.attachBaseContext(context)
    }
}