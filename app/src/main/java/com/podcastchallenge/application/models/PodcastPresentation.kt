package com.podcastchallenge.application.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.podcastchallenge.data.models.PodcastDTO

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

fun PodcastDTO.toPodcastPresentation() =
    PodcastPresentation(
        id = id.orEmpty(),
        title = title.orEmpty(),
        publisher = publisher.orEmpty(),
        description = description.orEmpty(),
        isFavorite = false,
        thumbnailUrl = thumbnail.orEmpty(),
        imageUrl = image.orEmpty()
    )