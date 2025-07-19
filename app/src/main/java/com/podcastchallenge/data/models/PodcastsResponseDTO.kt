package com.podcastchallenge.data.models

import com.google.gson.annotations.SerializedName

data class BestPodcastsDTO (
    val id: Long? = null,
    val name: String? = null,
    val total: Long? = null,
    @SerializedName("has_next")
    val hasNext: Boolean? = null,
    val podcasts: List<PodcastDTO> = emptyList(),
    @SerializedName("parent_id")
    val parentId: Long? = null,
    @SerializedName("page_number")
    val pageNumber: Long? = null,
    @SerializedName("has_previous")
    val hasPrevious: Boolean? = null,
    @SerializedName("listennotes_url")
    val listenNotesURL: String? = null,
    @SerializedName("next_page_number")
    val nextPageNumber: Long? = null,
    @SerializedName("previous_page_number")
    val previousPageNumber: Long? = null
)

data class PodcastDTO(
    val id: String? = null,
    val image: String? = null,
    val title: String? = null,
    val publisher: String? = null,
    val thumbnail: String? = null,
    val description: String? = null
)