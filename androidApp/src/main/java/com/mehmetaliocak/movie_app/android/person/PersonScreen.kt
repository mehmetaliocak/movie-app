package com.mehmetaliocak.movie_app.android.person

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mehmetaliocak.movie_app.android.composables.LoadMoreTextView
import com.mehmetaliocak.movie_app.android.composables.MovieView
import com.mehmetaliocak.movie_app.android.destinations.MovieDetailScreenDestination
import com.mehmetaliocak.movie_app.android.extension.formatDate
import com.mehmetaliocak.movie_app.models.Cast
import com.mehmetaliocak.movie_app.models.Movie
import com.mehmetaliocak.movie_app.models.Person
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Destination(navArgsDelegate = Cast::class)
@Composable
fun PersonScreen(navigator: DestinationsNavigator) {
    PersonScreen {
        navigator.navigate(MovieDetailScreenDestination(it))
    }
}

@Composable
private fun PersonScreen(
    viewModel: PersonViewModel = getViewModel(),
    onMovieClick: (Movie) -> Unit
) {
    val loading by viewModel.loading.collectAsState()
    val person by viewModel.person.collectAsState()
    val movies by viewModel.movies.collectAsState()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        if (loading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            person?.let {
                PersonDescriptionView(person = it)

                Text(
                    text = "Known For",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(8.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(movies) { movie ->
                        Box(modifier = Modifier.width(150.dp)) {
                            MovieView(movie = movie, onMovieClick = onMovieClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PersonDescriptionView(person: Person) {
    Column {
        Spacer(modifier = Modifier.padding(top = 16.dp))

        Row {
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            ) {
                SubcomposeAsyncImage(
                    model = person.profilePhotoURL(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.TopStart
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                Text(person.name, style = MaterialTheme.typography.body1)
                Text(person.placeOfBirth ?: "", style = MaterialTheme.typography.body1)
                Text(person.birthday.formatDate(), style = MaterialTheme.typography.body1)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LoadMoreTextView(person.biography, 5)
    }
}