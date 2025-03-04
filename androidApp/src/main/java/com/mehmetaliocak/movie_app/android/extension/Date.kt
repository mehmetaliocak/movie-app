package com.mehmetaliocak.movie_app.android.extension

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String?.formatDate(): String {
    if (this == null) return " - "
    val date = toDAte("yyyy-MM-dd") ?: return " - "
    return DateFormat.format("dd MMM, yyyy", date).toString()
}


private fun String.toDAte(format: String): Date? {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.parse(this)
}