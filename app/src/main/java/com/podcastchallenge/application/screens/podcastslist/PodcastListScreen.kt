package com.podcastchallenge.application.screens.podcastslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.podcastchallenge.application.ui.components.PodcastErrorComponent
import com.podcastchallenge.application.components.PodcastItem
import com.podcastchallenge.application.ui.components.PodcastTopAppBar
import com.podcastchallenge.application.models.PodcastPresentation
import com.podcastchallenge.application.ui.theme.PodcastChallengeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import com.podcastchallenge.R
import com.podcastchallenge.application.ui.components.PodcastLoader

@Composable
fun PodcastListScreen(
    onNavigateToDetails: (PodcastPresentation) -> Unit
){
    val viewModel: PodcastListViewModel = hiltViewModel()
    val podcastListPagingState = viewModel.podcastsPagingFlow.collectAsLazyPagingItems()

    PodcastListContent(
        podcastListPagingState = podcastListPagingState,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
fun PodcastListContent(
    podcastListPagingState: LazyPagingItems<PodcastPresentation>,
    onNavigateToDetails: (PodcastPresentation) -> Unit
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
            items(podcastListPagingState.itemCount){index ->
                val podcast = podcastListPagingState[index]
                podcast?.let {
                    PodcastItem(
                        title = it.title,
                        publisher = it.publisher,
                        thumbnailUrl = it.thumbnailUrl,
                        isFavorite = it.isFavorite,
                        onClick = {
                            onNavigateToDetails(it)
                        }
                    )
                }
            }

            if (podcastListPagingState.loadState.append is LoadState.Loading) {
                item {
                    PodcastLoader(modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                    )
                }
            }

            if (podcastListPagingState.loadState.append is LoadState.Error) {
                item {
                    PodcastErrorComponent(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }

        if (podcastListPagingState.loadState.refresh is LoadState.Loading) {
            PodcastLoader(
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        if (podcastListPagingState.loadState.refresh is LoadState.Error) {
            PodcastErrorComponent(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PodcastListScreenPreview() {
    fun fakePodcastList(): List<PodcastPresentation> = listOf(
        PodcastPresentation.mock(),
        PodcastPresentation.mock(),
        PodcastPresentation.mock(),
        PodcastPresentation.mock(),
        PodcastPresentation.mock()
    )
    val fakeFlow = remember {
        MutableStateFlow(PagingData.from(fakePodcastList()))
    }

    val fakePagingState = fakeFlow.collectAsLazyPagingItems()
    PodcastChallengeTheme {
        PodcastListContent(
            podcastListPagingState = fakePagingState,
            onNavigateToDetails = {}
        )
    }
}