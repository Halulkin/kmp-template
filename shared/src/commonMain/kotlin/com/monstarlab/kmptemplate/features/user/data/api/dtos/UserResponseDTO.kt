package com.monstarlab.kmptemplate.features.user.data.api.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDTO(
    val data: UserDTO
)
