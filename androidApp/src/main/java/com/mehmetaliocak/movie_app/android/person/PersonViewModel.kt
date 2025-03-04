package com.mehmetaliocak.movie_app.android.person

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetaliocak.movie_app.android.navArgs
import com.mehmetaliocak.movie_app.api.PersonService
import com.mehmetaliocak.movie_app.models.Cast
import com.mehmetaliocak.movie_app.models.Movie
import com.mehmetaliocak.movie_app.models.Person
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PersonViewModel(
    private val personService: PersonService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    private val _person = MutableStateFlow<Person?>(null)
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val loading = _loading.asStateFlow()
    val person = _person.asStateFlow()
    val movies = _movies.asStateFlow()

    init {
        getPersonDetails(savedStateHandle.navArgs())
    }

    private fun getPersonDetails(cast: Cast) {
        if (_loading.value) {
            return
        }

        _loading.update { true }

        viewModelScope.launch {
            val personDetail = async { personService.getDetails(cast.id) }
            val credits = async { personService.getCredits(cast.id) }
            _person.update { personDetail.await() }
            _movies.update { credits.await().cast }
            _loading.update { false }
        }
    }
}