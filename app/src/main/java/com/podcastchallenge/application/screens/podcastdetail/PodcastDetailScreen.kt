package com.podcastchallenge.application.screens.podcastdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.podcastchallenge.R
import com.podcastchallenge.application.ui.components.PodcastAsyncImage
import com.podcastchallenge.application.ui.components.PodcastTopAppBar
import com.podcastchallenge.application.models.PodcastPresentation
import com.podcastchallenge.application.screens.podcastdetail.PodcastDetailEvents.*
import com.podcastchallenge.application.ui.components.HtmlText
import com.podcastchallenge.application.ui.theme.PodcastChallengeTheme

@Composable
fun PodcastDetailScreen(
    podcast: PodcastPresentation,
    onNavigateBack: () -> Unit
) {
    val viewModel: PodcastDetailViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onDispatch(CheckIfFavorite(podcast.id))
    }

    PodcastDetailContent(
        podcast = podcast,
        state = state,
        onToggleFavorite = {
            viewModel.onDispatch(ToggleFavorite(podcast))
        },
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun PodcastDetailContent(
    podcast: PodcastPresentation,
    state: PodcastDetailState,
    onToggleFavorite: () -> Unit,
    onNavigateBack: () -> Unit
){
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
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            podcast.apply {
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
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
                        .padding(horizontal = 64.dp)
                        .padding(vertical = 24.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.accessibility_podcast_image, title)
                )
                Button(
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = .7f)
                    ),
                    onClick = onToggleFavorite,
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = stringResource(
                            if (state.isFavorite) R.string.favourited else R.string.favourite
                        ),
                        fontSize = 18.sp,
                    )
                }
                HtmlText(
                    modifier = Modifier
                        .padding(
                            top = 24.dp,
                            bottom = 16.dp
                        ),
                    html = description
                )
            }
        }
    }
}

@Preview
@Composable
fun PodcastDetailScreenPreview(){
    PodcastChallengeTheme {
        PodcastDetailContent(
            podcast = PodcastPresentation.mock(),
            state = PodcastDetailState(isFavorite = true),
            onNavigateBack = {},
            onToggleFavorite = {}
        )
    }
}