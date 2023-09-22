package com.monstarlab.kmptemplate.features.resources.domain.repository

import com.monstarlab.kmptemplate.features.resources.domain.model.Resource

interface ResourceRepository {
    suspend fun get(): List<Resource>
}
