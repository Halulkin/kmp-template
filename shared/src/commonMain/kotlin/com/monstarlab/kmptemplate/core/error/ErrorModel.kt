package com.monstarlab.kmptemplate.core.error

import com.monstarlab.kmptemplate.core.network.errorhandling.ApiException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.utils.io.errors.IOException

sealed interface ErrorModel {

    data class ApiError(val exception: ApiException) : ErrorModel

    sealed class Connection : ErrorModel {
        object Timeout : Connection()
        object IOError : Connection()
    }

    data class Unknown(val throwable: Throwable) : ErrorModel
}

fun Throwable.toError(): ErrorModel {
    return when (this) {
        is ApiException -> ErrorModel.ApiError(this)
        is SocketTimeoutException -> ErrorModel.Connection.Timeout
        is IOException -> ErrorModel.Connection.IOError
        else -> ErrorModel.Unknown(this)
    }
}
