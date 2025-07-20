package com.podcastchallenge.application.screens.podcastslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.podcastchallenge.R
import com.podcastchallenge.application.components.PodcastTopAppBar
import com.podcastchallenge.application.ui.theme.PodcastChallengeTheme

@Composable
fun PodcastListScreen(
    onNavigateToDetails: () -> Unit
){
    Scaffold(
        topBar = {
            PodcastTopAppBar(
                title = stringResource(R.string.podcasts)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PodcastListScreenPreview(){
    PodcastChallengeTheme {
        PodcastListScreen(
            onNavigateToDetails = {}
        )
    }
}