package com.jemy.thamanya.ui.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jemy.thamanya.R
import com.jemy.thamanya.ui.ui.common.AppBar
import com.jemy.thamanya.ui.ui.themes.backgroundColor
import com.jemy.thamanya.ui.ui.themes.primaryColor
import java.net.URLEncoder

@Composable
fun Search(
    navController: NavController
) {
    //   val aboutUs = hiltViewModel<SearchViewModel>().aboutUs.collectAsState().value
    Scaffold(topBar = {
        AppBar(
            title = stringResource(R.string.search),
            onMenuClick = {
                navController.popBackStack()
            },
            icon = Icons.AutoMirrored.Filled.ArrowBack
        )
    }, content = { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            //   Log.d("HOMECOMPOSE", "getAboutUs: ${aboutUs.toString()}")
            SearchContainer(navController, categoriesResponse)
        }

    })
}


@Composable
private fun SearchContainer(navController: NavController, categoriesResponse: CategoriesResponse) {
    var searchText by remember { mutableStateOf("") }
    var catExpanded by remember { mutableStateOf(false) }
    var subCatExpanded by remember { mutableStateOf(false) }
    val title = stringResource(R.string.categories)
    val subTitle = stringResource(R.string.sub_categories)
    var catSelectedOption by remember { mutableStateOf(title) }
    var subCatSelectedOption by remember { mutableStateOf(subTitle) }
    val categories = categoriesResponse.categories
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text(text = stringResource(R.string.search_here)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Dropdown Button
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick = { catExpanded = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = catSelectedOption,
                        color = Color.Black,
                    )
                }

                // Dropdown Menu with Rounded Corners
                DropdownMenu(
                    expanded = catExpanded,
                    onDismissRequest = { catExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)

                ) {
                    categories.forEach { cats ->
                        DropdownMenuItem(
                            text = { Text(cats.name ?: "Unknown") },
                            onClick = {
                                catSelectedOption = cats.name ?: "Unknown"
                                subCatSelectedOption = subTitle
                                catExpanded = false
                            }
                        )
                    }
                }
            }

            // sub category dropdown
            if (catSelectedOption != stringResource(R.string.categories) && catSelectedOption.isNotEmpty()) {
                val selectedCat = categories.find { catSelectedOption == it.name }
                val subCats = selectedCat?.subCategories ?: listOf()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Dropdown Button
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        onClick = { subCatExpanded = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            textAlign = TextAlign.Start,
                            text = subCatSelectedOption,
                            color = Color.Black,
                        )
                    }

                    // Dropdown Menu with Rounded Corners
                    DropdownMenu(
                        expanded = subCatExpanded,
                        onDismissRequest = { subCatExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)

                    ) {
                        subCats.forEach { subcats ->
                            DropdownMenuItem(
                                text = { Text(subcats.name ?: "Unknown") },
                                onClick = {
                                    subCatSelectedOption = subcats.name ?: "Unknown"
                                    subCatExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    if (catSelectedOption == title) catSelectedOption = ""
                    if (subCatSelectedOption == subTitle) subCatSelectedOption = ""
                    navigateToSearchResult(
                        navController,
                        catSelectedOption,
                        subCatSelectedOption,
                        searchText
                    )
                },
                modifier = Modifier
                    .padding(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                )
            ) {
                Spacer(Modifier.width(8.dp))

                Text(stringResource(R.string.search), color = Color.White)
            }
        }
    }
}

private fun navigateToSearchResult(
    navController: NavController,
    catId: String?,
    subCatId: String?,
    key: String?
) {
    val route = buildString {
        append("SearchResult")

        val params = mutableListOf<String>()

        catId?.takeIf { it.isNotBlank() }?.let {
            params.add("catId=${URLEncoder.encode(it, "UTF-8")}")
        }

        subCatId?.takeIf { it.isNotBlank() }?.let {
            params.add("subCatId=${URLEncoder.encode(it, "UTF-8")}")
        }

        key?.takeIf { it.isNotBlank() }?.let {
            params.add("key=${URLEncoder.encode(it, "UTF-8")}")
        }

        if (params.isNotEmpty()) {
            append("?")
            append(params.joinToString("&"))
        }
    }

    navController.navigate(route)
}
