package com.mehmetaliocak.movie_app.models

import kotlinx.serialization.Serializable

@Serializable
data class PersonCreditsResponse(
    val cast: List<Movie>
)
