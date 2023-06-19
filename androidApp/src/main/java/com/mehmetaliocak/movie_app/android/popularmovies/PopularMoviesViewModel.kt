package com.mehmetaliocak.movie_app.android.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.mehmetaliocak.movie_app.api.MovieService
import com.mehmetaliocak.movie_app.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PopularMoviesViewModel(private val moviesPagingSource: PopularMoviesPagingSource) : ViewModel() {

    val movies = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = { moviesPagingSource }
    ).flow.cachedIn(viewModelScope)
}