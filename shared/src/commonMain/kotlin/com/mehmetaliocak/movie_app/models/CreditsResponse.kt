package com.mehmetaliocak.movie_app.models

import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    val cast: List<Cast>
)