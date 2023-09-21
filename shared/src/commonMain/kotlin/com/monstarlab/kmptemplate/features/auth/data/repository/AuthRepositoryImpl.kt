package com.monstarlab.kmptemplate.features.auth.data.repository

import com.monstarlab.kmptemplate.features.auth.domain.models.AuthToken
import com.monstarlab.kmptemplate.features.auth.domain.repository.AuthRepository
import com.monstarlab.kmptemplate.features.auth.data.api.AuthApi
import com.monstarlab.kmptemplate.features.auth.data.api.dtos.toAuthToken

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(email: String, password: String): AuthToken {
        println("AuthRepositoryImpl: Logging in")
        val responseBody = api.postLogin(email, password)
        println("AuthRepositoryImpl: Logged in: $responseBody")
        return responseBody.toAuthToken()
    }
}
