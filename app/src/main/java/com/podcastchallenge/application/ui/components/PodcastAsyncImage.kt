package com.podcastchallenge.application.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.podcastchallenge.R

@Composable
fun PodcastAsyncImage(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.None,
    contentDescription: String? = null
){
    AsyncImage(
        modifier = modifier,
        model = imageUrl,
        placeholder = painterResource(R.drawable.placeholder_image),
        error = painterResource(R.drawable.placeholder_image),
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}

@Preview
@Composable
private fun PodcastAsyncImagePreview(){
    PodcastAsyncImage(
        modifier = Modifier,
        imageUrl = ""
    )
}