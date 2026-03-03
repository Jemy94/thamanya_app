package com.jemy.thamanya.ui.ui.screens.search

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.jemy.thamanya.R
import com.jemy.thamanya.ui.ui.common.AppBar
import com.jemy.thamanya.ui.ui.common.SurahList
import com.jemy.thamanya.ui.ui.screens.home.SimpleError
import com.jemy.thamanya.utils.UiState

@Composable
fun SearchResult(
    navController: NavController,
    catId: String = "",
    subCatId: String = "",
    key: String = ""
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    // ✅ load once when inputs change (NOT every recomposition)
    LaunchedEffect(catId, subCatId, key) {
        viewModel.onIntent(SearchIntent.Load(catId, subCatId, key))
    }

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.search_text),
                onMenuClick = { navController.popBackStack() },
                icon = Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (val s = state.results) {
                UiState.Idle -> Unit

                UiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is UiState.Error -> {
                    SimpleError(
                        message = s.message,
                        onRetry = { viewModel.onIntent(SearchIntent.Retry) },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is UiState.Success -> {
                    val list = s.data
                    Log.d("HOMECOMPOSE", "Search results size: ${list.size}")

                    if (list.isEmpty()) {
                        Text(
                            "No results found",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        SurahList(
                            items = list,
                            onItemClick = { item ->
                                Log.d("HOMECOMPOSE", "clicked: ${item.name}")
                                val data = Uri.encode(Gson().toJson(item))
                                navController.navigate("SurahDetails/$data")
                            }
                        )
                    }
                }
            }
        }
    }
}