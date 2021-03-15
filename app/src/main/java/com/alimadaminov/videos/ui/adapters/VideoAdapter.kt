package com.alimadaminov.videos.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alimadaminov.videos.R
import com.alimadaminov.videos.databinding.ItemVideoBinding
import com.alimadaminov.videos.models.Video
import com.squareup.picasso.Picasso

class VideoAdapter(var videos:List<Video> = listOf(), var onNewsItemClick: () -> Unit) :
    RecyclerView.Adapter<VideoAdapter.ViewHolderNews>() {


    class ViewHolderNews(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemVideoBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNews {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)

        return ViewHolderNews(view)
    }

    override fun onBindViewHolder(holder: ViewHolderNews, position: Int) {
        var video = videos[position]
        Log.d("myLog", "onBindViewHolder: Title ${video.title}")
        holder.binding.txtTitle.text = video.title
        holder.binding.txtDate.text = video.updated
        Picasso.get().load(video.thumbnail).into(holder.binding.imgThumbnail)
    }

    override fun getItemCount(): Int = videos.size

    fun refreshVideos(_videos:List<Video>) {
        videos = _videos
        notifyDataSetChanged()
    }

}