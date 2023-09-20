package com.monstarlab.kmptemplate.android.features.nstack.domain.usecase

import com.monstarlab.kmptemplate.core.extensions.suspendRunCatching
import dk.nodes.nstack.kotlin.NStack
import dk.nodes.nstack.kotlin.models.AppOpenData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.Result
import dk.nodes.nstack.kotlin.models.Result as NstackResult

class SetupNstackUseCase(
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Result<AppOpenData> = suspendRunCatching {
        withContext(ioDispatcher) {
            when (val result = NStack.appOpen()) {
                is NstackResult.Success -> result.value.data
                is NstackResult.Error -> throw Exception("Failed to appOpen NStack")
            }
        }
    }
}
