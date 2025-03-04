package com.mehmetaliocak.movie_app.models

import com.mehmetaliocak.movie_app.util.asPhotoUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cast(
    val id: Long,
    val name: String,
    val character: String,
    @SerialName("profile_path")
    val profilePath: String?,
) {

    fun profilePhotoURL(): String {
        return profilePath.asPhotoUrl()
    }
}