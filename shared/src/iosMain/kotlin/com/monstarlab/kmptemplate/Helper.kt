package com.monstarlab.kmptemplate

import com.monstarlab.kmptemplate.di.sharedModules
import com.monstarlab.kmptemplate.features.login.ui.LoginViewModel
import com.monstarlab.kmptemplate.features.resources.ui.ResourcesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class LoginViewModelHelper : KoinComponent {
    val loginViewModel: LoginViewModel by inject()
}

class ResourcesViewModelHelper : KoinComponent {
    private val resourcesViewModel: ResourcesViewModel by inject()
}

fun initKoin() {
    startKoin {
        modules(sharedModules())
    }
}