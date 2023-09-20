package com.monstarlab.kmptemplate.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun commonModules() = listOf(
    coroutinesModule
)

val coroutinesModule = module {
    single<CoroutineDispatcher>(qualifier = named("DefaultDispatcher")) { Dispatchers.Default }
    single<CoroutineDispatcher>(qualifier = named("IoDispatcher")) { Dispatchers.IO }
    single<CoroutineDispatcher>(qualifier = named("MainDispatcher")) { Dispatchers.Main }
    single<CoroutineDispatcher>(qualifier = named("MainImmediateDispatcher")) { Dispatchers.Main.immediate }
}
