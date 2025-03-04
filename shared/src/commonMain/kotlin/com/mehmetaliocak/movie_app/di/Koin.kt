package com.mehmetaliocak.movie_app.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private const val BASE_URL = "api.themoviedb.org"
private const val API_KEY = "insert you key here"
expect fun platformModule(): Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule(), platformModule())
}

fun initKoin() {
    initKoin { }
}
fun commonModule() = module {
    single { createJson() }
    single { createHttpClient(get(), get()) }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }


fun createHttpClient(httpClientEngine: HttpClientEngine, json: Json) = HttpClient(httpClientEngine) {
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = BASE_URL
            path("3/")
            parameters.append("api_key", API_KEY)
        }
    }
    install(ContentNegotiation) {
        json(json)
    }
}