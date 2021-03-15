package com.alimadaminov.videos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimadaminov.videos.database.VideosDatabase
import com.alimadaminov.videos.databinding.ActivityMainBinding
import com.alimadaminov.videos.models.Video
import com.alimadaminov.videos.ui.adapters.VideoAdapter
import com.alimadaminov.videos.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var viewModel = MainViewModel.Factory(VideosDatabase.getInstance(this))
            .create(MainViewModel::class.java)
        var videoAdapter = VideoAdapter() {

        }

        viewModel.getVideosLive().observe(this, Observer {
            Log.d("myLog", "onCreate MainAct: ${it.size}")
            videoAdapter.refreshVideos(it)
        })


        binding.recycler.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, RecyclerView.VERTICAL, false
            )
        }

    }
}