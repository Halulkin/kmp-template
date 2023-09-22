package com.monstarlab.kmptemplate.android.di

import com.monstarlab.kmptemplate.android.features.login.ui.LoginViewModel
import com.monstarlab.kmptemplate.android.features.main.MainActivityViewModel
import com.monstarlab.kmptemplate.android.features.nstack.domain.usecase.SetupNstackUseCase
import com.monstarlab.kmptemplate.android.features.resources.ResourcesViewModel
import com.monstarlab.kmptemplate.features.auth.data.api.AuthApiImpl
import com.monstarlab.kmptemplate.features.auth.data.repository.AuthRepositoryImpl
import com.monstarlab.kmptemplate.features.login.domain.usecase.LoginUseCase
import com.monstarlab.kmptemplate.features.resources.data.api.ResourcesApiImpl
import com.monstarlab.kmptemplate.features.resources.data.repository.ResourceRepositoryImpl
import com.monstarlab.kmptemplate.features.resources.domain.usecase.GetResourcesUseCase
import com.monstarlab.kmptemplate.features.user.data.api.UsersApiImpl
import com.monstarlab.kmptemplate.features.user.data.repository.UserRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun androidModules() = listOf(
    mainActivityModule,
    loginModule,
    authModule,
    userModule,
    resourcesModule
)

val mainActivityModule = module {
    viewModel { MainActivityViewModel(get()) }
    single { SetupNstackUseCase(get(named("IoDispatcher"))) }
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
}