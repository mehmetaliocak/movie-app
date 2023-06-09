package com.mehmetaliocak.movie_app.util

fun String?.asPhotoUrl(): String {
    return "https://image.tmdb.org/t/p/original${this ?: ""}"
}