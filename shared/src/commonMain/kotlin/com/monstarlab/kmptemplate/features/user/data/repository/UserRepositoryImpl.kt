package com.monstarlab.kmptemplate.features.user.data.repository

import com.monstarlab.kmptemplate.features.user.data.api.UsersApi
import com.monstarlab.kmptemplate.features.user.data.api.dtos.toUser
import com.monstarlab.kmptemplate.features.user.domain.model.User
import com.monstarlab.kmptemplate.features.user.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: UsersApi
//    private val userPreferenceStore: UserPreferenceStore
) : UserRepository {


    override suspend fun get(): User {
        return api.getUser().data.toUser().also {
//            userPreferenceStore.add(it)
        }
    }
}
