package com.monstarlab.kmptemplate.features.user.data.api

import com.monstarlab.kmptemplate.features.user.data.api.dtos.UserResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class UsersApiImpl(
    private val httpClient: HttpClient
) : UsersApi {
    override suspend fun getUser(): UserResponseDTO {
        return httpClient.get(urlString = "users/2").body<UserResponseDTO>()
    }
}
