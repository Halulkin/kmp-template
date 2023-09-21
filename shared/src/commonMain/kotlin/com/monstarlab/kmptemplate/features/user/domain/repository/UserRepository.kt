package com.monstarlab.kmptemplate.features.user.domain.repository

import com.monstarlab.kmptemplate.features.user.domain.model.User

interface UserRepository {
    suspend fun get(): User
}
