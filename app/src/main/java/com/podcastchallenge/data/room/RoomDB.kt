package com.podcastchallenge.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PodcastEntity::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {
    abstract fun podcastDao(): PodcastDao
}