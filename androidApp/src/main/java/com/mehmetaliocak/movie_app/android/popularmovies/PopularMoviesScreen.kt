package com.mehmetaliocak.movie_app.android.popularmovies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mehmetaliocak.movie_app.android.composables.MovieView
import com.mehmetaliocak.movie_app.android.destinations.MovieDetailScreenDestination
import com.mehmetaliocak.movie_app.models.Movie
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun PopularMoviesScreen(navigator: DestinationsNavigator) {
    PopularMoviesScreen(
        onMovieClick = {
            navigator.navigate(MovieDetailScreenDestination(navArgs = it))
        }
    )
}
@Composable
private fun PopularMoviesScreen(
    onMovieClick: (Movie) -> Unit,
    viewModel: PopularMoviesViewModel = getViewModel()
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(movies) { movie ->
                if (movie != null) {
                    MovieView(movie = movie, onMovieClick)
                }
            }

            if (movies.loadState.refresh == LoadState.Loading) {
                item(
                    span = { GridItemSpan(maxLineSpan) }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

private fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyGridItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}