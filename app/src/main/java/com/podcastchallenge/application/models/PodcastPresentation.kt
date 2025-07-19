package com.podcastchallenge.application.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

data class PodcastPresentation(
    val id: String,
    val title: String,
    val publisher: String,
    val description: String,
    val isFavorite: Boolean,
    val thumbnailUrl: String,
    val imageUrl: String
) {
    companion object {
        fun mock(isFavorite: Boolean = false) : PodcastPresentation =
            PodcastPresentation(
                id = "",
                title = "Title",
                publisher = "Publisher",
                description = LoremIpsum(50).values.joinToString(),
                isFavorite = isFavorite,
                thumbnailUrl = "",
                imageUrl = ""
            )
    }
}