package com.monstarlab.kmptemplate.di


import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.append
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL = "reqres.in/api"


fun sharedModules() = listOf(
    coroutinesModule,
    networkModule,
)

val coroutinesModule = module {
    single<CoroutineDispatcher>(qualifier = named("DefaultDispatcher")) { Dispatchers.Default }
    single<CoroutineDispatcher>(qualifier = named("IoDispatcher")) { Dispatchers.IO }
    single<CoroutineDispatcher>(qualifier = named("MainDispatcher")) { Dispatchers.Main }
    single<CoroutineDispatcher>(qualifier = named("MainImmediateDispatcher")) { Dispatchers.Main.immediate }
}

val networkModule = module {
    single {
        HttpClient {
            expectSuccess = true
            addDefaultResponseValidation()
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(HttpRequestRetry) {
                retryOnServerErrors()
                exponentialDelay()
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
            install(DefaultRequest) {
                headers {
                    append(HttpHeaders.ContentType, ContentType.Application.Json)
                }
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                }
            }
        }
    }
}