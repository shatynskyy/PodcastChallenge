package com.podcastchallenge.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class PodcastEntity(
    @PrimaryKey
    val id: String
)
