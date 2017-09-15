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

// TODO: Apply once we start requesting trailers
//data class Trailers(
//        val id: Int,
//        val results: List<TrailerItem>
//)
//
///**
// * Object for individual Trailers
// */
//data class TrailerItem(
//        val id: Int,
//        val iso_639_1: String,
//        val iso_3166_1: String,
//        val key: String,
//        val name: String,
//        val site: String,
//        val size: Int,
//        val type: String
//)