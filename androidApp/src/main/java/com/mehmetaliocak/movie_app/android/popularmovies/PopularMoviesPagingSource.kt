package com.mehmetaliocak.movie_app.android.popularmovies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mehmetaliocak.movie_app.api.MovieService
import com.mehmetaliocak.movie_app.models.Movie

class PopularMoviesPagingSource(
    private val service: MovieService
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = service.getPopularMovies(page = page)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}