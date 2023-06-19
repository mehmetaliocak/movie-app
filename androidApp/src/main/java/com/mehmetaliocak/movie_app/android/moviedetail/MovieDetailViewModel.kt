package com.mehmetaliocak.movie_app.android.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetaliocak.movie_app.android.navArgs
import com.mehmetaliocak.movie_app.api.CreditsService
import com.mehmetaliocak.movie_app.api.MovieService
import com.mehmetaliocak.movie_app.models.Cast
import com.mehmetaliocak.movie_app.models.Movie
import com.mehmetaliocak.movie_app.models.MovieDetail
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val movieService: MovieService,
    private val creditsService: CreditsService
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    private val _movieDetail = MutableStateFlow<MovieDetail?>(null)
    private val _cast = MutableStateFlow<List<Cast>>(emptyList())
    val loading = _loading.asStateFlow()
    val movieDetail = _movieDetail.asStateFlow()
    val cast = _cast.asStateFlow()

    init {
        getMovieDetail(savedStateHandle.navArgs())
    }

    private fun getMovieDetail(movie: Movie) {
        if (_loading.value) {
            return
        }

        _loading.update { true }

        viewModelScope.launch {
            val movieDetail = async { movieService.getMovieDetail(movie.id) }
            val credits = async { creditsService.getCredits(movie.id) }
            _movieDetail.update { movieDetail.await() }
            _cast.update { credits.await().cast }
            _loading.update { false }
        }
    }
}