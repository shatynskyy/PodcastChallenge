package com.podcastchallenge.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PodcastDao {

    @Query("SELECT * FROM favorites")
    fun getFavoritesFlow(): Flow<List<PodcastEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(podcast: PodcastEntity)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    fun isFavoritePodcastFlow(id: String): Flow<Boolean>

}
