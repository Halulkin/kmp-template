package com.monstarlab.kmptemplate.android.di

import com.monstarlab.kmptemplate.android.features.main.MainActivityViewModel
import com.monstarlab.kmptemplate.android.features.nstack.domain.usecase.SetupNstackUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun androidModules() = listOf(
    mainActivityModule,
)

val mainActivityModule = module {
    viewModel { MainActivityViewModel(get()) }
    single { SetupNstackUseCase(get(named("IoDispatcher"))) }
}
