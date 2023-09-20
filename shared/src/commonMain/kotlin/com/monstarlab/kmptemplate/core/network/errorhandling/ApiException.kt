package com.monstarlab.kmptemplate.core.network.errorhandling

import io.ktor.utils.io.errors.IOException

/**
 * Represents the error that happened while communicating with REST API
 * @param code - HTTP code of the response
 * @param displayableMessage - message that can displayed to the user
 */
data class ApiException(
    val code: Int,
    val displayableMessage: String
) : IOException(displayableMessage)
