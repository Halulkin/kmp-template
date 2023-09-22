package com.monstarlab.kmptemplate.features.resources.data.repository

import com.monstarlab.kmptemplate.features.resources.domain.model.Resource
import com.monstarlab.kmptemplate.features.resources.domain.repository.ResourceRepository
import com.monstarlab.kmptemplate.features.resources.data.api.ResourcesApi
import com.monstarlab.kmptemplate.features.resources.data.api.dtos.toResource

class ResourceRepositoryImpl(
    private val api: ResourcesApi,
//    private val resourcePreferenceStore: ResourcePreferenceStore
) : ResourceRepository {

    override suspend fun get(): List<Resource> {
        return api.getResources().data.map { it.toResource() }.also {
//            resourcePreferenceStore.addAll(it)
        }
    }
}
