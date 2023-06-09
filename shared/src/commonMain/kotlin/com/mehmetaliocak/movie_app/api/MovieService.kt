package com.mehmetaliocak.movie_app.api

import com.mehmetaliocak.movie_app.models.MovieDetail
import com.mehmetaliocak.movie_app.models.MoviesResponse
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieService : KoinComponent {

    private val client: HttpClient by inject()

    @NativeCoroutines
    suspend fun getPopularMovies(page: Int): MoviesResponse {
        return client.get("movie/popular") {
            url {
                parameters.append("page", "$page")
            }
        }.body()
    }

    @NativeCoroutines
    suspend fun getMovieDetail(id: Long): MovieDetail {
        return client.get("movie/$id").body()
    }
}