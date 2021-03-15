package com.alimadaminov.videos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alimadaminov.videos.models.Video

@Database(entities = [Video::class], exportSchema = false, version = 1)
abstract class VideosDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao

    companion object {

        @Volatile
        private var INSTANCE: VideosDatabase? = null

        fun getInstance(context: Context): VideosDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VideosDatabase::class.java,
                        "videos_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance

            }
        }

    }
}