package com.monstarlab.kmptemplate.features.login.ui

import com.monstarlab.kmptemplate.core.error.ErrorModel

/**
 * Shared UI State that represents LoginScreen for IOS and Android
 **/
data class LoginState(
    val email: String = "eve.holt@reqres.in",
    val password: String = "cityslicka",
    val isLoading: Boolean = false,
    val error: ErrorModel? = null,
    val loginButtonEnabled: Boolean = false,
    val isLoggedIn: Boolean = false
)