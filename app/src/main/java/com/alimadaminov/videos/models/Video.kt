package com.alimadaminov.videos.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "video")
data class Video(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    val title: String,
    val description: String,
    val updated: String,
    val thumbnail: String,
    val url: String
)