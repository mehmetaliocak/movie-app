package com.mehmetaliocak.movie_app.api

import com.mehmetaliocak.movie_app.models.CreditsResponse
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreditsService : KoinComponent {
    private val client: HttpClient by inject()

    @NativeCoroutines
    suspend fun getCredits(id: Long): CreditsResponse {
        return client.get("movie/$id/credits").body()
    }
}