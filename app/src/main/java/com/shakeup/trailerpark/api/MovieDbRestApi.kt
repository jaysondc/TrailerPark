package com.shakeup.trailerpark.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Jayson on 9/14/2017.
 *
 * Initializes the Retrofit instance and provides methods for accessing different endpoints
 * provided by TheMovieDB API
 */

class MovieDbRestApi {

    private val movieDbApi: MovieDbApi

    init {
        // Initialize a retrofit instance with the given base URL
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        // MovieDbApi::class.java returns the .class representation of MovieDbApi.kt
        movieDbApi = retrofit.create(MovieDbApi::class.java)
    }

    fun getNowPlaying(page: Int): Call<NowPlayingResponse> = movieDbApi.getNowPlaying(page)
    fun getTrailers(movieId: Int): Call<TrailersResponse> = movieDbApi.getTrailers(movieId)
}