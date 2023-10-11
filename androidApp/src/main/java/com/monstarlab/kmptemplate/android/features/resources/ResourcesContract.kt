package com.monstarlab.kmptemplate.android.features.resources

import com.monstarlab.kmptemplate.features.resources.domain.model.Resource

/**
 * Resources Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ResourcesActions(
    val onResourceClick: (Resource) -> Unit = {}
)
