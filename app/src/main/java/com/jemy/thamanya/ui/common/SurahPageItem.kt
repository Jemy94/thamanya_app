package com.jemy.thamanya.ui.ui.common

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jemy.thamanya.R
import com.jemy.thamanya.ui.ui.themes.primaryColor

@Composable
fun SurahPagerSlider(
    images: List<CorporatesResponse.Corporate.Chapter>,
    onButtonClick: (CorporatesResponse.Corporate.Chapter) -> Unit = {},
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            CategoryImage(
                imageUrl = images[pagerState.currentPage].image,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Button at the bottom (equivalent to MaterialButton)
        Button(
            onClick = { onButtonClick(images[pagerState.currentPage]) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor // colorPrimary
            ),
            shape = RoundedCornerShape(0.dp) // Square corners like MaterialButton
        ) {
            Text(
                text = stringResource(R.string.activities),
                fontSize = 20.sp, // text_size_xlarge
                fontWeight = FontWeight.Bold
            )
        }
    }

    Log.d("SLIDER", "slider size is:  " + images.size)
}