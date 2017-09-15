package com.shakeup.trailerpark.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Jayson on 9/14/2017.
 *
 * Provides methods to access different MovieDB API endpoints through Retrofit.
 */

interface MovieDbApi {
    @GET("movie/now_playing")
    fun getNowPlaying(
            @Query("page") page: Int,
            @Query("api_key") key: String = "981e7bf7ef48337e4de5f5887593736b"): Call<NowPlayingResponse>
}