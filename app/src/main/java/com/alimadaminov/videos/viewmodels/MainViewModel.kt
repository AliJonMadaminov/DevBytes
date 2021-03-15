package com.alimadaminov.videos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alimadaminov.videos.database.VideosDatabase
import com.alimadaminov.videos.models.RootJson
import com.alimadaminov.videos.models.Video
import com.alimadaminov.videos.models.VideoApi
import com.alimadaminov.videos.repositories.MainRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.lang.IllegalStateException

class MainViewModel private constructor(videosDatabase: VideosDatabase) : ViewModel() {

    private var mainRepo = MainRepo(videosDatabase)
    private var functionCall= mainRepo.fetchOrRefresh()
    fun getVideosLive() = mainRepo.videosLive

    class Factory(var videosDatabase: VideosDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(videosDatabase) as T
            }
            throw IllegalStateException("You can pass only MainViewModel")
        }

    }

}
