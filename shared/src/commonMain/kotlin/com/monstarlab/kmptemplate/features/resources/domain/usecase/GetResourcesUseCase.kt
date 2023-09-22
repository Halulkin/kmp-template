package com.monstarlab.kmptemplate.features.resources.domain.usecase

import com.monstarlab.kmptemplate.core.extensions.suspendRunCatching
import com.monstarlab.kmptemplate.features.resources.domain.repository.ResourceRepository
import kotlinx.coroutines.delay

class GetResourcesUseCase(
    private val resourceRepository: ResourceRepository
) {

    suspend operator fun invoke() = suspendRunCatching {
        delay(2000)
        resourceRepository.get()
    }
}
