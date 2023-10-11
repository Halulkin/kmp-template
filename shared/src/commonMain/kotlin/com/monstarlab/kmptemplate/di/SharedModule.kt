package com.monstarlab.kmptemplate.di


import com.monstarlab.kmptemplate.features.auth.data.api.AuthApiImpl
import com.monstarlab.kmptemplate.features.auth.data.repository.AuthRepositoryImpl
import com.monstarlab.kmptemplate.features.login.domain.usecase.LoginUseCase
import com.monstarlab.kmptemplate.features.login.ui.LoginViewModel
import com.monstarlab.kmptemplate.features.resources.data.api.ResourcesApiImpl
import com.monstarlab.kmptemplate.features.resources.data.repository.ResourceRepositoryImpl
import com.monstarlab.kmptemplate.features.resources.domain.usecase.GetResourcesUseCase
import com.monstarlab.kmptemplate.features.resources.ui.ResourcesViewModel
import com.monstarlab.kmptemplate.features.user.data.api.UsersApiImpl
import com.monstarlab.kmptemplate.features.user.data.repository.UserRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
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
    authModule,
    userModule,
    resourcesModule,
    loginModule
)

val authModule = module {
    single { AuthApiImpl(get()) }
    single { AuthRepositoryImpl(get() as AuthApiImpl) }
}

val userModule = module {
    single { UsersApiImpl(get()) }
    single { UserRepositoryImpl(get() as UsersApiImpl) }
}

val resourcesModule = module {
    single { ResourcesViewModel(get()) }
    single { GetResourcesUseCase(get() as ResourceRepositoryImpl) }
    single { ResourceRepositoryImpl(get() as ResourcesApiImpl) }
    single { ResourcesApiImpl(get()) }
}

val loginModule = module {
    single { LoginViewModel(get()) }
    single {
        LoginUseCase(
            get() as AuthRepositoryImpl,
            get() as UserRepositoryImpl
        )
    }
}


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