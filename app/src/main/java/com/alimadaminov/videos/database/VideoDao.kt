package com.alimadaminov.videos.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alimadaminov.videos.models.Video

@Dao
interface VideoDao {

    @Insert
    fun insertVideo(video: Video)

    @Update
    fun updateVideo(video: Video)

    @Query("SELECT * FROM video")
    fun queryAllVideos():LiveData<List<Video>>

    @Query("SELECT COUNT(*) FROM video")
    fun queryRowCount():Int

    @Delete
    fun deleteVideo(video: Video)
}