package com.monstarlab.kmptemplate.features.auth.data.api

import com.monstarlab.kmptemplate.features.auth.data.api.dtos.TokenResponseDTO

interface AuthApi {
    suspend fun postLogin(email: String, password: String): TokenResponseDTO
}
