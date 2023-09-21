package com.monstarlab.kmptemplate.android.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.kmptemplate.android.features.nstack.domain.usecase.SetupNstackUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainActivityViewModel(
    private val setupNstackUseCase: SetupNstackUseCase
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(MainActivityState())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        setupNstack()
    }

    fun onNstackDataConsumed() = viewModelScope.launch {
        _stateFlow.update { it.copy(nStackData = null) }
    }

    private fun setupNstack() = viewModelScope.launch {
        val data = setupNstackUseCase().getOrNull()
        _stateFlow.update {
            it.copy(
                nStackData = data,
                showSplash = false
            )
        }
    }
}
