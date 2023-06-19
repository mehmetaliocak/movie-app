package com.mehmetaliocak.movie_app.android.extension

fun Double.formatToSingleDigit(): String {
    return String.format(format = "%.1f", this)
}