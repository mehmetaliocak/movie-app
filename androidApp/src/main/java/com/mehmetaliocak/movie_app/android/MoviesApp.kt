package com.mehmetaliocak.movie_app.android

import android.app.Application
import com.mehmetaliocak.movie_app.android.moviedetail.MovieDetailViewModel
import com.mehmetaliocak.movie_app.android.person.PersonViewModel
import com.mehmetaliocak.movie_app.android.popularmovies.PopularMoviesPagingSource
import com.mehmetaliocak.movie_app.android.popularmovies.PopularMoviesViewModel
import com.mehmetaliocak.movie_app.api.MovieService
import com.mehmetaliocak.movie_app.api.CreditsService
import com.mehmetaliocak.movie_app.api.PersonService
import com.mehmetaliocak.movie_app.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@MoviesApp)
            modules(appModule)
        }
    }
}

private val appModule = module {
    singleOf(::MovieService)
    singleOf(::CreditsService)
    singleOf(::PersonService)

    factoryOf(::PopularMoviesPagingSource)

    viewModelOf(::PopularMoviesViewModel)
    viewModelOf(::MovieDetailViewModel)
    viewModelOf(::PersonViewModel)
}