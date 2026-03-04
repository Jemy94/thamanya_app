package com.jemy.thamanya.ui.common

//noinspection SuspiciousImport
import android.R
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.jemy.thamanya.data.models.Content
import com.jemy.thamanya.data.models.Section
import com.jemy.thamanya.data.models.ViewType

//@Composable
//fun HomeCategoryItem(
//    category: CategoriesResponse.Category,
//    modifier: Modifier = Modifier,
//    onClick: (CategoriesResponse.Category) -> Unit = {}
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(150.dp)
//            .padding(8.dp),
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        onClick = { onClick(category) }
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(4.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//
//            // Image – 80dp similar to your XML
//            CategoryImage(
//                imageUrl = category.image,
//                modifier = Modifier
//                    .size(80.dp)
//                    .padding(top = 4.dp)
//            )
//
//            // Text – centered, maxLines=2, ellipsize=end
//            Text(
//                text = category.name.orEmpty(),
//                modifier = Modifier
//                    .padding(top = 8.dp)
//                    .fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                fontSize = 14.sp,            // ~ text_size_small
//                color = Color(0xFF000000)    // md_black
//            )
//        }
//    }
//}
//
//@Composable
//fun CategoryImage(
//    imageUrl: String?,
//    modifier: Modifier = Modifier
//) {
//    val placeholder = painterResource(R.drawable.ic_menu_report_image)
//
//    Log.d("IMAGE" , "imageURL: ${imageUrl}");
//    AsyncImage(
//        model = imageUrl,
//        contentDescription = null,
//        modifier = modifier,
//        contentScale = ContentScale.Crop,
//        placeholder = placeholder,
//        error = placeholder
//    )
//}
//
//@Composable
//fun HomeCategoryList(
//    modifier: Modifier = Modifier,
//    items: List<CategoriesResponse.Category>,
//    onItemClick: (CategoriesResponse.Category) -> Unit = {},
//    ) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        modifier = modifier,
//        contentPadding = PaddingValues(8.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(items) { category ->
//            HomeItem(
//                category = category,
//                onClick = onItemClick
//            )
//        }
//    }
//}

@Composable
fun SectionsScreen(
    sections: LazyPagingItems<Section>
) {
    LazyColumn {
        items(sections.itemCount) { index ->
            sections[index]?.let {
                SectionItem(it)
            }
        }

        sections.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContentItem(
    content: Content,
    viewType: ViewType
) {
    when (viewType) {
        ViewType.SQUARE -> SquareItem(content)
        ViewType.BIG_SQUARE -> BigSquareItem(content)
        ViewType.QUEUE -> QueueItem(content)
        ViewType.TWO_LINES_GRID -> QueueSmallItem(content)
    }
}

@Composable
fun SquareItem(content: Content) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .padding(end = 12.dp)
    ) {
        AsyncImage(
            model = content.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = content.name.orEmpty(),
            maxLines = 1
        )
    }
}

@Composable
fun BigSquareItem(content: Content) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(end = 12.dp)
    ) {
        AsyncImage(
            model = content.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.6f)
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
        )

        Text(
            text = content.name.orEmpty(),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}

@Composable
fun QueueItem(content: Content) {
    Row(
        modifier = Modifier
            .width(260.dp)
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = content.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = content.name.orEmpty(),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun SectionItem(section: Section) {

    val viewType = ViewType.from(section.type)
    val contentList = section.content.orEmpty().filterNotNull()

    Column {
        Text(
            text = section.name.orEmpty(),
            modifier = Modifier.padding(16.dp)
        )

        when (viewType) {

            ViewType.TWO_LINES_GRID -> {

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(contentList.chunked(2)) { pair ->
                        Column(
                            modifier = Modifier.padding(end = 12.dp)
                        ) {
                            pair.forEach {
                                QueueSmallItem(it)
                                Spacer(Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }

            else -> {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(contentList) { content ->
                        ContentItem(content, viewType)
                    }
                }
            }
        }
    }
}

@Composable
fun QueueSmallItem(content: Content) {
    Row(
        modifier = Modifier
            .width(220.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(6.dp)
    ) {
        AsyncImage(
            model = content.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = content.name.orEmpty(),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun TwoLinesGridSection(contents: List<Content>) {

    val grouped = contents.chunked(2)

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(grouped) { pair ->

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.width(260.dp)
            ) {

                pair.getOrNull(0)?.let {
                    QueueCompactItem(it)
                }

                pair.getOrNull(1)?.let {
                    QueueCompactItem(it)
                }
            }
        }
    }
}

@Composable
fun QueueCompactItem(content: Content) {
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            AsyncImage(
                model = content.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = content.name.orEmpty(),
                maxLines = 1
            )
        }
    }
}