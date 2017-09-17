package com.shakeup.trailerpark.commons;

import com.shakeup.trailerpark.commons.adapters.AdapterConstants
import com.shakeup.trailerpark.commons.adapters.ViewType

/**
 * Created by Jayson on 9/14/2017.
 *
 * Holds the model objects for Movie and Trailer items
 */

/**
 * Object returned by the Now Playing endpoint
 */
data class NowPlaying(
        val movies: List<MovieItem>,
        val page: Int,
        val total_results: Int,
        // val dates: DateRange,
        val total_pages: Int
)

/**
 * Object for individual movies
 */
data class MovieItem(
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
) : ViewType {

    override fun getViewType(): Int {
        when (vote_average >= 5) {
            true -> return AdapterConstants.MOVIE_HIGHLIGHTED
            false -> return AdapterConstants.MOVIE_NORMAL
        }
    }

    fun getBackdropPath300() : String = generateImagePath("w300")
    fun getBackdropPath780() : String = generateImagePath("w780")
    fun getBackdropPath1280() : String = generateImagePath("w1280")
    fun getBackdropPathOriginal() : String = generateImagePath("original")

    fun getPosterPath154() : String = generateImagePath("w154")
    fun getPosterPath185() : String = generateImagePath("w185")
    fun getPosterPath342() : String = generateImagePath("w342")
    fun getPosterPath500() : String = generateImagePath("w500")
    fun getPosterPathOriginal() : String = generateImagePath("original")

    // Image format = https://image.tmdb.org/t/p/[IMAGE_SIZE]/[IMAGE_PATH]
    private fun generateImagePath(size: String): String =
            String.format("https://image.tmdb.org/t/p/%s/%s", size, poster_path)
}

data class Trailers(
        val id: Int,
        val results: List<TrailerItem>
)

/**
 * Object for individual Trailers
 */
data class TrailerItem(
        val id: Int,
        val iso_639_1: String,
        val iso_3166_1: String,
        val key: String,
        val name: String,
        val site: String,
        val size: Int,
        val type: String
)


