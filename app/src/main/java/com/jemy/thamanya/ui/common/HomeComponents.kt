package com.jemy.thamanya.ui.common

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.jemy.thamanya.data.models.Content
import com.jemy.thamanya.data.models.Section
import com.jemy.thamanya.data.models.ViewType

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
fun SectionsScreenWithoutPaging(
    sections: List<Section>
) {
    LazyColumn {
        items(sections.size) { index ->
            sections[index].let {
                SectionItem(it)
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
fun SquareItem(
    content: Content
) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .padding(end = 12.dp)
    ) {

        AppCard(
            modifier = Modifier.size(140.dp),
        ) {
            Log.d("IMAGE", "SquareItem imageURL: ${content.avatarUrl}");
            AsyncImage(
                model = content.avatarUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = content.name.orEmpty(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun BigSquareItem(
    content: Content
) {
    AppCard(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(end = 12.dp)
    ) {
        Box {
            Log.d("IMAGE", "BigSquareItem imageURL: ${content.avatarUrl}");
            AsyncImage(
                model = content.avatarUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
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
                        )
                    )
            )

            Text(
                text = content.name.orEmpty(),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun QueueItem(
    content: Content,
) {
    AppCard(
        modifier = Modifier
            .width(260.dp)
            .padding(end = 12.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Log.d("IMAGE", " QueueItem imageURL: ${content.avatarUrl}");
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
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun SectionItem(section: Section) {

    val viewType = ViewType.from(section.type)
    val contentList = section.content.orEmpty().filterNotNull()

    Column {
        Text(
            text = section.name.orEmpty(),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
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
                                QueueSmallItem(content = it)
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
fun QueueSmallItem(
    content: Content,
) {
    AppCard(
        modifier = Modifier
            .width(220.dp),
    ) {
        Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Log.d("IMAGE", " QueueSmallItem imageURL: ${content.avatarUrl}");
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
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        content()
    }
}