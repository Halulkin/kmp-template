package com.monstarlab.kmptemplate.features.auth.data.api

import com.monstarlab.kmptemplate.features.auth.data.api.dtos.TokenResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.http.Parameters
import io.ktor.util.InternalAPI

class AuthApiImpl(private val httpClient: HttpClient) : AuthApi {
    @OptIn(InternalAPI::class)
    override suspend fun postLogin(email: String, password: String): TokenResponseDTO {
        return httpClient.post("login") {
            body = FormDataContent(Parameters.build {
                append("email", email)
                append("password", password)
            })
        }.body<TokenResponseDTO>()
    }
}
