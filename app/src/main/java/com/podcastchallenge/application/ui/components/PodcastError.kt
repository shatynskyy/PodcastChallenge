package com.podcastchallenge.application.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.podcastchallenge.R

@Composable
fun PodcastErrorComponent(
    modifier: Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.error),
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Red)
        )
    }
}