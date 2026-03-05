package com.jemy.thamanya.ui.screens.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.jemy.thamanya.R
import com.jemy.thamanya.ui.common.AppBar
import com.jemy.thamanya.ui.common.SectionsScreen
import com.jemy.thamanya.ui.screens.Navigation
import com.jemy.thamanya.utils.ResourceState
import kotlinx.coroutines.launch

@Composable
fun Home(
    navController: NavController
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val sections = viewModel.sectionsPager.collectAsLazyPagingItems()

    Scaffold (
        topBar = {
            AppBar(
                title = stringResource(R.string.app_name),
                onMenuClick = { navController.navigate(route = Navigation.Search.name) },
                icon = Icons.Default.Search
            )
        }
    ){ padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            when (sections.loadState.refresh) {

                is LoadState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is LoadState.Error -> {
                    val error =
                        (sections.loadState.refresh as LoadState.Error).error

                    SimpleError(
                        message = error.message ?: "Something went wrong",
                        onRetry = { sections.retry() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    SectionsScreen(sections)
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