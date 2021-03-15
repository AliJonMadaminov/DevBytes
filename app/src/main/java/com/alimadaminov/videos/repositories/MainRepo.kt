package com.alimadaminov.videos.repositories

import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase
import com.alimadaminov.videos.database.VideoDao
import com.alimadaminov.videos.database.VideosDatabase
import com.alimadaminov.videos.models.RootJson
import com.alimadaminov.videos.models.Video
import com.alimadaminov.videos.models.VideoApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepo(var videosDb: VideosDatabase) {


    var videosLive:LiveData<List<Video>> = videosDb.videoDao().queryAllVideos()


    fun fetchOrRefresh() {

        VideoApi.jokeService.getRootJson().enqueue(object : Callback<RootJson> {
            override fun onResponse(call: Call<RootJson>, response: Response<RootJson>) {
                if (response.isSuccessful) {
                    var responseBody = response.body()

                    if (responseBody != null) {
                        var job: Job = Job()
                        var scope = CoroutineScope(Dispatchers.IO + job)
                        scope.launch {
                            responseBody.videos?.let {
                                insertOrUpdateVideos(videosDb.videoDao(),
                                    it
                                )
                            }
                        }

                    }
                }
            }

            override fun onFailure(call: Call<RootJson>, t: Throwable) {
                throw t
            }
        })

    }

    fun insertOrUpdateVideos(videoDao:VideoDao, videos:List<Video>) {
        if (videoDao.queryRowCount() == 0) {
            for (video in videos) {
                videoDao.insertVideo(video)
            }
        } else {
            for (video in videos) {
                videoDao.updateVideo(video)
            }
        }
    }
}