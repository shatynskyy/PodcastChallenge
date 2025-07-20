package com.podcastchallenge.application.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.podcastchallenge.data.models.PodcastDTO
import com.podcastchallenge.data.room.PodcastEntity

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

fun PodcastDTO.toPodcastPresentation(isFavorite: Boolean) =
    PodcastPresentation(
        id = id.orEmpty(),
        title = title.orEmpty(),
        publisher = publisher.orEmpty(),
        description = description.orEmpty(),
        isFavorite = isFavorite,
        thumbnailUrl = thumbnail.orEmpty(),
        imageUrl = image.orEmpty()
    )

fun PodcastPresentation.toPodcastEntity() : PodcastEntity =
    PodcastEntity(id = id)
