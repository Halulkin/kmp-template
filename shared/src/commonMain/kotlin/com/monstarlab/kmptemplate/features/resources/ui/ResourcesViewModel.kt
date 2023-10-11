package com.monstarlab.kmptemplate.features.resources.ui

import com.monstarlab.kmptemplate.features.ViewModel
import com.monstarlab.kmptemplate.core.error.toError
import com.monstarlab.kmptemplate.features.resources.domain.usecase.GetResourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResourcesViewModel(
    private val getResourcesUseCase: GetResourcesUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ResourcesState> = MutableStateFlow(ResourcesState())
    val stateFlow: StateFlow<ResourcesState> = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true) }
            val result = getResourcesUseCase()
            _stateFlow.update { state ->
                state.copy(
                    isLoading = false,
                    resources = result.getOrDefault(emptyList()),
                    error = result.exceptionOrNull()?.toError()
                )
            }
        }
    }
}
