package com.podcastchallenge.application.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.podcastchallenge.data.models.PodcastDTO
import com.podcastchallenge.data.room.PodcastEntity

/**
 * UI model representing a podcast item in the app.
 *
 * Includes a companion mock generator for previews and testing.
 */
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

/**
 * Maps PodcastDTO to PodcastPresentation, adding isFavorite flag.
 */
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

/**
 * Maps PodcastPresentation to a database entity.
 */
fun PodcastPresentation.toPodcastEntity() : PodcastEntity =
    PodcastEntity(id = id)
