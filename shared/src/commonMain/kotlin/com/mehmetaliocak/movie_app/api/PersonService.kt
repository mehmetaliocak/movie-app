package com.mehmetaliocak.movie_app.api

import com.mehmetaliocak.movie_app.models.Person
import com.mehmetaliocak.movie_app.models.PersonCreditsResponse
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PersonService : KoinComponent {
    private val client: HttpClient by inject()

    @NativeCoroutines
    suspend fun getDetails(id: Long): Person {
        return client.get("person/$id").body()

    }
    @NativeCoroutines
    suspend fun getCredits(id: Long): PersonCreditsResponse {
        return client.get("person/$id/movie_credits").body()
    }
}