package com.monstarlab.kmptemplate.features.resources.ui

import com.monstarlab.kmptemplate.core.error.ErrorModel
import com.monstarlab.kmptemplate.features.resources.domain.model.Resource

/**
 * UI State that represents ResourcesScreen
 **/
data class ResourcesState(
    val isLoading: Boolean = false,
    val resources: List<Resource> = emptyList(),
    val error: ErrorModel? = null
)
