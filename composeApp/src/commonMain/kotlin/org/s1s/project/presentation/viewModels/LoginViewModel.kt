package org.s1s.project.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.s1s.project.domain.model.UserDomain
import org.s1s.project.domain.usecase.LoginUseCase
import org.s1s.project.utility.AppState

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginState = MutableStateFlow<AppState<UserDomain>>(AppState.Idle)
    val loginState: StateFlow<AppState<UserDomain>> = _loginState.asStateFlow()

    fun login(username: String, password: String) {
        _loginState.value = AppState.Loading
        viewModelScope.launch {
            loginUseCase(username, password).fold(
                onSuccess = { user ->
                    _loginState.value = AppState.Success(user)
                },
                onFailure = { error ->
                    _loginState.value = AppState.Error(error.message ?: "Login failed")
                }
            )
        }
    }
}