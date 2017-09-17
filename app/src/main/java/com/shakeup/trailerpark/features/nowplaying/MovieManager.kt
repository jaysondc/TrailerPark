package com.shakeup.trailerpark.features.nowplaying

import com.shakeup.trailerpark.api.MovieDbRestApi
import com.shakeup.trailerpark.commons.MovieItem
import com.shakeup.trailerpark.commons.NowPlaying
import com.shakeup.trailerpark.commons.TrailerItem
import com.shakeup.trailerpark.commons.Trailers
import rx.Observable

/**
 * Created by Jayson on 9/14/2017.
 *
 * Singleton manages calls to the passed in API.
 */
object MovieManager{
    private val api: MovieDbRestApi = MovieDbRestApi()
    /**
     * Returns Now Playing Movies on the given page
     * @param page indicates the next page navigate
     */
    fun getNowPlaying(page: Int): Observable<NowPlaying> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getNowPlaying(page)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body()
                val movies = dataResponse.results.map {
                    MovieItem(
                            it.vote_count,
                            it.id,
                            it.video,
                            it.vote_average,
                            it.title,
                            it.popularity,
                            it.poster_path,
                            it.original_language,
                            it.original_title,
                            it.genre_ids,
                            it.backdrop_path,
                            it.adult,
                            it.overview,
                            it.release_date
                    )
                }
                val nowPlaying = NowPlaying(
                        movies,
                        dataResponse.page,
                        dataResponse.total_results,
                        dataResponse.total_pages
                )

                // Return nowPlayin object
                subscriber.onNext(nowPlaying)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getTrailers(movieId: Int) : Observable<Trailers> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getTrailers(movieId)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body()
                val trailerList = dataResponse.youtube.map {
                    TrailerItem(
                            it.name,
                            it.size,
                            it.source,
                            it.type
                    )
                }
                val trailers = Trailers(
                        dataResponse.id,
                        trailerList
                )

                // Return trailers object
                subscriber.onNext(trailers)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}