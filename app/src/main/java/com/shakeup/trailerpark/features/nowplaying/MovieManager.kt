package com.shakeup.trailerpark.features.nowplaying

import com.shakeup.trailerpark.commons.Models
import com.shakeup.trailerpark.commons.NowPlaying

/**
 * Created by Jayson on 9/14/2017.
 *
 * Manages calls to the passed in API.
 */
class MovieManager {

    /**
     *
     * Returns Now Playing Movies on the given page
     *
     * @param page indicates the next page navigate
     */
//    fun getNowPlaying(page: Int): Observable<NowPlaying> {
//        return Observable.create {
//            subscriber ->
//            val callResponse = api.getNowPlaying(page)
//            val response = callResponse.execute()
//
//            if (response.isSuccessful) {
//                val dataResponse = response.body().data
//                val movies = dataResponse.children.map {
//                    val item = it.data
//                    MovieItem(item.author, item.title, item.num_comments,
//                            item.created, item.thumbnail, item.url)
//                }
//                val redditNews = RedditNews(
//                        dataResponse.after ?: "",
//                        dataResponse.before ?: "",
//                        movies)
//
//                subscriber.onNext(redditNews)
//                subscriber.onCompleted()
//            } else {
//                subscriber.onError(Throwable(response.message()))
//            }
//        }
//    }

    /**
     * getNowPlaying that returns dummy data
     */

    fun getNowPlaying() : NowPlaying {
        var movieList = listOf(
                Models.dummyMovieItem.dummyMovie,
                Models.dummyMovieItem.dummyMovie,
                Models.dummyMovieItem.dummyMovie,
                Models.dummyMovieItem.dummyMovie,
                Models.dummyMovieItem.dummyMovie)

        return NowPlaying(
                movies = movieList,
                page = 1,
                total_results = 100,
                total_pages = 10
        )

    }
}