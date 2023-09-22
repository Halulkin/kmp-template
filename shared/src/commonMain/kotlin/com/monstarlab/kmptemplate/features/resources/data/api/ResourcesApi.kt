package com.monstarlab.kmptemplate.features.resources.data.api

import com.monstarlab.kmptemplate.features.resources.data.api.dtos.ResourcesResponseDTO

interface ResourcesApi {

    suspend fun getResources(page: Int = 1): ResourcesResponseDTO
}
