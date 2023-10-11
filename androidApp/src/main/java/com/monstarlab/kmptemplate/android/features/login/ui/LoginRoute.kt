package com.monstarlab.kmptemplate.android.features.login.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.monstarlab.kmptemplate.android.core.ui.effects.SystemUISideEffect
import com.monstarlab.kmptemplate.features.login.ui.LoginState

@Composable
fun LoginRoute(
    coordinator: LoginCoordinator = rememberLoginCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(LoginState())

    val useDarkIcons = !isSystemInDarkTheme()
    SystemUISideEffect { controller ->
        controller.setSystemBarsColor(Color.Transparent, useDarkIcons)
    }

    // UI Actions
    val actions = rememberLoginActions(coordinator)

    // UI Rendering
    LoginScreen(uiState, actions)

}

@Composable
fun rememberLoginActions(coordinator: LoginCoordinator): LoginActions {
    return remember(coordinator) {
        LoginActions(
            onEmailChange = coordinator.viewModel::onEmailChange,
            onLoginClick = coordinator.viewModel::login,
            onPasswordChange = coordinator.viewModel::onPasswordChange
        )
    }
}
