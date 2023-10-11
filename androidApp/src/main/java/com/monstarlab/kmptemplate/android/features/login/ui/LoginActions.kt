package com.monstarlab.kmptemplate.android.features.login.ui


/**
 * Login Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LoginActions(
    val onPasswordChange: (String) -> Unit = {},
    val onEmailChange: (String) -> Unit = {},
    val onLoginClick: () -> Unit = {}
)
