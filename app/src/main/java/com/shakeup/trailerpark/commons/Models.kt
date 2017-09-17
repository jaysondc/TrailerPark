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

    fun getBackdropPath300() : String = generateBackdropPath("w300")
    fun getBackdropPath780() : String = generateBackdropPath("w780")
    fun getBackdropPath1280() : String = generateBackdropPath("w1280")
    fun getBackdropPathOriginal() : String = generateBackdropPath("original")

    fun getPosterPath154() : String = generatePosterPath("w154")
    fun getPosterPath185() : String = generatePosterPath("w185")
    fun getPosterPath342() : String = generatePosterPath("w342")
    fun getPosterPath500() : String = generatePosterPath("w500")
    fun getPosterPathOriginal() : String = generatePosterPath("original")

    // Image format = https://image.tmdb.org/t/p/[IMAGE_SIZE]/[IMAGE_PATH]
    private fun generatePosterPath(size: String): String =
            String.format("https://image.tmdb.org/t/p/%s/%s", size, poster_path)

    private fun generateBackdropPath(size: String): String =
            String.format("https://image.tmdb.org/t/p/%s/%s", size, backdrop_path)
}

/*
 * Data class to hold a MovieID and list of trailers
 */
data class Trailers(
        val id: Int,
        val youtube: List<TrailerItem>
)

/*
 * Data class to hold individual trailers
 */
data class TrailerItem(
        val name: String,
        val size: String,
        val source: String,
        val type: String
) {

    fun generatePreviewHQ(): String = generatePreviewPath("hqdefault")
    fun generatePreviewMQ(): String = generatePreviewPath("mqdefault")
    fun generatePreviewSD(): String = generatePreviewPath("sddefault")

    // Youtube image path is https://img.youtube.com/vi/<insert-youtube-video-id-here>/hqdefault.jpg
    private fun generatePreviewPath(quality: String): String =
            String.format("https://img.youtube.com/vi/%s/%s.jpg", source, quality)

    // Youtube video path format is http://www.youtube.com/watch?v=<youtube-video-source>
    fun generateVideoUrl(): String = String.format("http://www.youtube.com/watch?v=%s", source)
}


