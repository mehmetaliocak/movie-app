package com.mehmetaliocak.movie_app.android.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mehmetaliocak.movie_app.android.Colors
import com.mehmetaliocak.movie_app.android.destinations.PersonScreenDestination
import com.mehmetaliocak.movie_app.android.extension.formatDate
import com.mehmetaliocak.movie_app.android.extension.formatToSingleDigit
import com.mehmetaliocak.movie_app.models.Cast
import com.mehmetaliocak.movie_app.models.Movie
import com.mehmetaliocak.movie_app.models.MovieDetail
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Destination(navArgsDelegate = Movie::class)
@Composable
fun MovieDetailScreen(navigator: DestinationsNavigator) {
    MovieDetailScreen(onPersonClick = {
        navigator.navigate(PersonScreenDestination(navArgs = it))
    })
}

@Composable
fun MovieDetailScreen(
    onPersonClick: (Cast) -> Unit, viewModel: MovieDetailViewModel = getViewModel()
) {
    val loading by viewModel.loading.collectAsState()
    val movieDetail by viewModel.movieDetail.collectAsState()
    val casts by viewModel.cast.collectAsState()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        if (loading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            movieDetail?.let {
                MovieDescriptionView(movieDetail = it)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Cast",
                    Modifier.padding(8.dp),
                    style = MaterialTheme.typography.subtitle1
                )

                LazyRow(
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(casts) { cast ->
                        CastView(cast = cast, onPersonClick = onPersonClick)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun MovieDescriptionView(movieDetail: MovieDetail) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
        ) {
            SubcomposeAsyncImage(
                model = movieDetail.backdropURL(),
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

        Spacer(Modifier.height(16.dp))

        Column(
            Modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "${movieDetail.title} - ${movieDetail.voteAverage.formatToSingleDigit()}",
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                text = movieDetail.releaseDate.formatDate(), style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                movieDetail.genres.forEach { genre ->
                    Text(
                        text = genre.name,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(Colors.orange, RoundedCornerShape(16.dp))
                            .padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = movieDetail.overview, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
private fun CastView(cast: Cast, onPersonClick: (Cast) -> Unit) {
    Column(
        Modifier
            .width(200.dp)
            .border(0.5.dp, Color.Gray, RoundedCornerShape(16.dp))
            .clickable { onPersonClick.invoke(cast) }
    ) {
        SubcomposeAsyncImage(
            model = cast.profilePhotoURL(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.TopStart,
            modifier = Modifier
                .aspectRatio(0.75f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
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

        Text(
            text = cast.name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = cast.character,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))
    }
}