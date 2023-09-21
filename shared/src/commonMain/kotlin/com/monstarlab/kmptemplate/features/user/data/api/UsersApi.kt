package com.monstarlab.kmptemplate.features.user.data.api

import com.monstarlab.kmptemplate.features.user.data.api.dtos.UserResponseDTO

interface UsersApi {
    suspend fun getUser(): UserResponseDTO
}
