package com.monstarlab.kmptemplate.android.features.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun ResourcesRoute(
    coordinator: ResourcesCoordinator
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(ResourcesState())

    // UI Actions
    val actions = rememberResourcesActions(coordinator)

    // UI Rendering
    ResourcesScreen(uiState, actions)
}

@Composable
fun rememberResourcesActions(coordinator: ResourcesCoordinator): ResourcesActions {
    return remember(coordinator) {
        ResourcesActions(coordinator::openResourceDetails)
    }
}
