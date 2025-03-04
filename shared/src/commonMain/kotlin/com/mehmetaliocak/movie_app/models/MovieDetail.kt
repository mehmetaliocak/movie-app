package com.mehmetaliocak.movie_app.models

import com.mehmetaliocak.movie_app.util.asPhotoUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetail(
    val id: Long,
    val title: String,
    val genres: List<Genre>,
    @SerialName("backdrop_path")
    private val backdropPath: String?,
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("vote_average")
    val voteAverage: Double,
) {
    fun backdropURL(): String {
        return backdropPath.asPhotoUrl()
    }
}