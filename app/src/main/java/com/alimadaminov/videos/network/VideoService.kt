package com.alimadaminov.videos.models

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val BASE_URL = "https://devbytes.udacity.com"

interface VideoService {

    @GET("/devbytes.json")
    fun getRootJson():Call<RootJson>

}

val retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

object VideoApi {
    val jokeService: VideoService by lazy { retrofit.create(VideoService::class.java) }
}