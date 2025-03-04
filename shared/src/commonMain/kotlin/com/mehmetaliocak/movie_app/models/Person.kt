package com.mehmetaliocak.movie_app.models

import com.mehmetaliocak.movie_app.util.asPhotoUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: Long,
    val name: String,
    @SerialName("place_of_birth")
    val placeOfBirth: String?,
    val biography: String,
    val birthday: String?,
    @SerialName("profile_path")
    val profilePath: String?
) {

    fun profilePhotoURL(): String {
        return profilePath.asPhotoUrl()
    }
}
