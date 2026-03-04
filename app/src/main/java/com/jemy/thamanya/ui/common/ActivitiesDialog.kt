package com.jemy.thamanya.ui.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.jemy.thamanya.R
import com.jemy.thamanya.ui.common.CategoryImage


@Composable
fun ActivitiesDialogScreen(
    parts: List<CorporatesResponse.Corporate.Chapter.Part>,
    onCloseClick: () -> Unit,
    onActivityClick: (CorporatesResponse.Corporate.Chapter.Part) -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
    ) {
        val (closeLayout, partsLayout) = createRefs()

        // 🔴 Close Layout (Icon + Text)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .zIndex(1f)
                .constrainAs(closeLayout) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable { onCloseClick() }
        ) {
            Card(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.White),
                shape = CircleShape,

                ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_close_green),
                        contentDescription = "Close",
                        modifier = Modifier.size(32.dp)
                    )
                }

            }

            Text(
                text = stringResource(id = R.string.close),
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight
            )
        }

        // 🟩 Parts Layout
        Card(
            modifier = Modifier
                .zIndex(0f)
                .constrainAs(partsLayout) {
                    top.linkTo(closeLayout.bottom, margin = (-52).dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(12.dp),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 24.dp,
                bottomStart = 24.dp,
                bottomEnd = 0.dp
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column(
                modifier = Modifier.padding(vertical = 36.dp, horizontal = 16.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(parts) { item ->
                        PartItem(part = item, onClick = onActivityClick)
                    }
                }
            }
        }

    }
}

@Composable
fun PartItem(
    part: CorporatesResponse.Corporate.Chapter.Part,
    modifier: Modifier = Modifier,
    onClick: (CorporatesResponse.Corporate.Chapter.Part) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { onClick(part) }
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
                imageUrl = part.image,
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 4.dp)
            )

            // Text – centered, maxLines=2, ellipsize=end
            Text(
                text = part.name.orEmpty(),
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesBottomSheet(
    parts: List<CorporatesResponse.Corporate.Chapter.Part.File>,
    onDismiss: () -> Unit,
    onActivityClick: (CorporatesResponse.Corporate.Chapter.Part.File) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
    ) {
        // 👇 YOUR CUSTOM UI HERE
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(parts) {
                SheikhItem(
                    modifier = Modifier.fillMaxWidth(),
                    category = it,
                    onClick = onActivityClick,

                    )
            }
        }

    }
}

@Composable
fun SheikhItem(
    category: CorporatesResponse.Corporate.Chapter.Part.File,
    modifier: Modifier = Modifier,
    onClick: (CorporatesResponse.Corporate.Chapter.Part.File) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { onClick(category) }
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            // ✅ Image card with FIXED width
            Card(
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                CategoryImage(
                    imageUrl = category.readerImage,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // ✅ Text column now has space
            Column(
                modifier = Modifier
                    .weight(1f) // 👈 IMPORTANT
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(R.string.shekh),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = category.readerName.orEmpty(),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}