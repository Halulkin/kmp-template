package com.monstarlab.kmptemplate.features.login.ui

import com.monstarlab.kmptemplate.core.error.toError
import com.monstarlab.kmptemplate.features.ViewModel
import com.monstarlab.kmptemplate.features.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val stateFlow: StateFlow<LoginState> = _stateFlow.asStateFlow()

    fun onEmailChange(value: String) {
        _stateFlow.update { state ->
            state.copy(email = value, error = null)
        }
    }

    fun onPasswordChange(value: String) {
        _stateFlow.update { state ->
            state.copy(password = value, error = null)
        }
    }

    fun login() {
        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true) }
            val state = _stateFlow.value
            val result = loginUseCase(state.email, state.password)
            println("LoginViewModel: Logged in: $result")
            _stateFlow.update { loginState ->
                loginState.copy(
                    error = result.exceptionOrNull()?.toError(),
                    isLoading = false,
                    isLoggedIn = result.isSuccess
                )
            }
        }
    }
}
