package com.monstarlab.kmptemplate.features.resources.data.api

import com.monstarlab.kmptemplate.features.resources.data.api.dtos.ResourcesResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ResourcesApiImpl(private val httpClient: HttpClient) : ResourcesApi {
    override suspend fun getResources(page: Int): ResourcesResponseDTO {
        return httpClient.get("unknown") {
            parameter("page", page)
        }.body<ResourcesResponseDTO>()
    }
}
