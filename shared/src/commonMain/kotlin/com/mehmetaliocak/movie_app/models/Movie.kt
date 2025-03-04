package com.mehmetaliocak.movie_app.models

import com.mehmetaliocak.movie_app.util.asPhotoUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Long,
    val title: String,
    @SerialName("poster_path")
    val posterPath: String?,
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("vote_average")
    val voteAverage: Double
) {

    fun posterUrl():String {
        return posterPath.asPhotoUrl()
    }
}