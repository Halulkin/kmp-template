package com.monstarlab.kmptemplate.android.features.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.compose.koinInject


/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class LoginCoordinator(
    val viewModel: LoginViewModel,
    val scope: CoroutineScope
) {
    val screenStateFlow = viewModel.stateFlow

    init {
        viewModel.stateFlow
            .filter { it.isLoggedIn }
            .onEach {
                println("LoginCoordinator: Logged in")
            }
            .launchIn(scope)
    }
}

@Composable
fun rememberLoginCoordinator(
    viewModel: LoginViewModel = koinInject(),
    scope: CoroutineScope = rememberCoroutineScope()
): LoginCoordinator {
    return remember(viewModel, scope) {
        LoginCoordinator(
            viewModel = viewModel,
            scope = scope
        )
    }
}
