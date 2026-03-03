package com.jemy.thamanya.ui.ui.common

//noinspection SuspiciousImport
import android.R
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun HomeCategoryItem(
    category: CategoriesResponse.Category,
    modifier: Modifier = Modifier,
    onClick: (CategoriesResponse.Category) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { onClick(category) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // Image – 80dp similar to your XML
            CategoryImage(
                imageUrl = category.image,
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 4.dp)
            )

            // Text – centered, maxLines=2, ellipsize=end
            Text(
                text = category.name.orEmpty(),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,            // ~ text_size_small
                color = Color(0xFF000000)    // md_black
            )
        }
    }
}

@Composable
fun CategoryImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    val placeholder = painterResource(R.drawable.ic_menu_report_image)

    Log.d("IMAGE" , "imageURL: ${imageUrl}");
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        placeholder = placeholder,
        error = placeholder
    )
}

@Composable
fun HomeCategoryList(
    modifier: Modifier = Modifier,
    items: List<CategoriesResponse.Category>,
    onItemClick: (CategoriesResponse.Category) -> Unit = {},
    ) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { category ->
            HomeCategoryItem(
                category = category,
                onClick = onItemClick
            )
        }
    }
}