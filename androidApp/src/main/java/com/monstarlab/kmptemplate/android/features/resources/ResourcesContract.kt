package com.monstarlab.kmptemplate.android.features.resources

import com.monstarlab.kmptemplate.features.resources.domain.model.Resource
import com.monstarlab.kmptemplate.core.error.ErrorModel

/**
 * UI State that represents ResourcesScreen
 **/
data class ResourcesState(
    val isLoading: Boolean = false,
    val resources: List<Resource> = emptyList(),
    val error: ErrorModel? = null
)

/**
 * Resources Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ResourcesActions(
    val onResourceClick: (Resource) -> Unit = {}
)
