package com.podcastchallenge.application.screens.podcastdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.podcastchallenge.R
import com.podcastchallenge.application.components.PodcastAsyncImage
import com.podcastchallenge.application.components.PodcastTopAppBar
import com.podcastchallenge.application.models.PodcastPresentation
import com.podcastchallenge.ui.theme.PodcastChallengeTheme

@Composable
fun PodcastDetailScreen(
    podcast: PodcastPresentation,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            PodcastTopAppBar(
                onNavigationIconClick = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            podcast.apply {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = publisher,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
                PodcastAsyncImage(
                    imageUrl = imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 16.dp,
                            horizontal = 48.dp
                        )
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.accessibility_podcast_image, title)
                )
                Button(
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = .7f)
                    ),
                    onClick = { },
                ) {
                    Text(
                        text = stringResource(
                            if (isFavorite) R.string.favourited else R.string.favourite
                        ),
                        fontSize = 18.sp,
                    )
                }
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = description,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun PodcastDetailScreenPreview(){
    PodcastChallengeTheme {
        PodcastDetailScreen(
            podcast = PodcastPresentation.mock(isFavorite = false),
            onNavigateBack = { }
        )
    }
}