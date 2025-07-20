package com.podcastchallenge.application.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PodcastLoader(modifier: Modifier){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator(color = Color.Red.copy(alpha = .7f))
    }
}