package com.shakeup.trailerpark.api

/**
 * Created by Jayson on 9/14/2017.
 *
 * Models provided to the Moshi JSON parser to parse responses
 */
class NowPlayingResponse(
        val results: List<MovieResponse>,
        val page: Int,
        val total_results: Int,
        // val dates: DateRange,
        val total_pages: Int
)

/*
 * JSON parsing for individual movie responses
 */
class MovieResponse(
        val vote_count: Int,
        val id: Int,
        val video: Boolean,
        val vote_average: Double,
        val title: String,
        val popularity: Float,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val genre_ids: List<Int>,
        val backdrop_path: String?,
        val adult: Boolean,
        val overview: String,
        val release_date: String
)

/*
 * JSON parsing for a Trailers response
 */
class TrailersResponse(
        val id: Int,
        val youtube: List<TrailerItemResponse>
)

/**
 * JSON Parsing for individual trailers
 */
class TrailerItemResponse(
        val name: String,
        val size: String,
        val source: String,
        val type: String
)