package com.mehmetaliocak.movie_app.models

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Long,
    val name: String
)