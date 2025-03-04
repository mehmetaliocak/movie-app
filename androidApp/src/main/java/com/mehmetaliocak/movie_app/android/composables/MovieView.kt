package com.mehmetaliocak.movie_app.android.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mehmetaliocak.movie_app.android.extension.formatDate
import com.mehmetaliocak.movie_app.android.extension.formatToSingleDigit
import com.mehmetaliocak.movie_app.models.Movie

@Composable
fun MovieView(
    movie: Movie,
    onMovieClick: (Movie) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.5f)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onMovieClick(movie) }
    ) {
        SubcomposeAsyncImage(
            model = movie.posterUrl(),
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
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            movie.voteAverage.formatToSingleDigit(),
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .background(Color.Black.copy(0.7f), CircleShape)
                                .padding(8.dp),
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Spacer(Modifier.weight(1f))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black.copy(0.7f))
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            movie.title,
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            movie.releaseDate.formatDate(),
                            style = MaterialTheme.typography.caption,
                            color = Color.White,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}