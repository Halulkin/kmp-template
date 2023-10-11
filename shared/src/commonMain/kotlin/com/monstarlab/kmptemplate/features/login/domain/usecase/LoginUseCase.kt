package com.monstarlab.kmptemplate.features.login.domain.usecase

import com.monstarlab.kmptemplate.features.user.domain.repository.UserRepository
import com.monstarlab.kmptemplate.core.extensions.suspendRunCatching
import com.monstarlab.kmptemplate.features.auth.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(email: String, password: String) = suspendRunCatching {
        authRepository.login(email, password)
        userRepository.get()
    }
}
