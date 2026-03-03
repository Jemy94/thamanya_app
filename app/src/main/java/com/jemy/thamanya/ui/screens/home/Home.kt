package com.jemy.thamanya.ui.ui.screens.home


import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.jemy.thamanya.R
import com.jemy.thamanya.ui.ui.common.AppBar
import com.jemy.thamanya.ui.ui.common.HomeCategoryList
import com.jemy.thamanya.ui.ui.common.ImageSlider
import com.jemy.thamanya.ui.screens.Navigation
import com.jemy.thamanya.ui.ui.screens.SharedViewModel
import com.jemy.thamanya.ui.ui.screens.home.mvicontract.HomeIntent
import com.jemy.thamanya.utils.UiState
import kotlinx.coroutines.launch

@Composable
fun Home(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val categoriesData = (state.categories as? UiState.Success)?.data
    val bannersData = (state.banners as? UiState.Success)?.data

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Column(
                        modifier = Modifier
                            .width(280.dp)
                            .fillMaxHeight()
                            .padding(24.dp)
                    ) {
                        Text(
                            stringResource(R.string.welcome_message_day),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Spacer(Modifier.height(16.dp))

                        DrawerItem(
                            icon = Icons.Default.Home,
                            text = stringResource(R.string.home_page_title),
                            onClick = { scope.launch { drawerState.close() } }
                        )
                        DrawerItem(
                            icon = Icons.Default.Search,
                            text = stringResource(R.string.search),
                            onClick = {
                                scope.launch {
                                    drawerState.close()

                                    if (categoriesData != null) {
                                        val encoded = Uri.encode(Gson().toJson(categoriesData))
                                        navController.navigate("Search/$encoded")
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Categories not loaded yet",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        )

                        DrawerItem(
                            painter = painterResource(R.drawable.ic_news),
                            text = stringResource(R.string.news_string),
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    navController.navigate(route = Navigation.News.name)
                                }
                            }
                        )

                        DrawerItem(
                            icon = Icons.Default.Call,
                            text = stringResource(R.string.contact_us),
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    navController.navigate(route = Navigation.ContactUs.name)
                                }
                            }
                        )

                        DrawerItem(
                            icon = Icons.Default.Info,
                            text = stringResource(R.string.about_app),
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    navController.navigate(route = Navigation.AboutUs.name)
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                AppBar(
                    title = stringResource(R.string.home_page_title),
                    onMenuClick = { scope.launch { drawerState.open() } },
                    icon = Icons.Default.Menu
                )
            }
        ) { padding ->

            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {

                val isLoading =
                    state.categories is UiState.Loading || state.banners is UiState.Loading

                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    state.categories is UiState.Error -> {
                        val msg = (state.categories as UiState.Error).message
                        SimpleError(
                            message = msg,
                            onRetry = { viewModel.onIntent(HomeIntent.Retry) },
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    state.banners is UiState.Error -> {
                        val msg = (state.banners as UiState.Error).message
                        SimpleError(
                            message = msg,
                            onRetry = { viewModel.onIntent(HomeIntent.Retry) },
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    state.categories is UiState.Success && state.banners is UiState.Success -> {
                        // Safe because we are in Success branch
                        val cats = categoriesData!!
                        val bans = bannersData!!

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            ImageSlider(images = bans.banners)

                            HomeCategoryList(
                                items = cats.categories,
                                onItemClick = { cat ->
                                    Log.d("HOMECOMPOSE", "Category clicked: ${cat.name}")
                                    sharedViewModel.setSubcategories(cat)
                                    navController.navigate(route = Navigation.SubCategory.name)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SimpleError(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Spacer(Modifier.height(12.dp))
        Button(onClick = onRetry) { Text("Retry") }
    }
}

@Composable
fun DrawerItem(
    icon: ImageVector? = null,
    painter: Painter? = null,
    text: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (painter != null) {
            Icon(

                painter = painter,
                contentDescription = text,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Icon(
                imageVector = icon!!,
                contentDescription = text,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}