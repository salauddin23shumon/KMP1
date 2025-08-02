package org.s1s.project.presentation.viewModels

import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.s1s.project.presentation.navigation.NavigationManager
import org.s1s.project.utility.AppState

class LoginViewModel(private val navigationManager: NavigationManager) : ViewModel(), KoinComponent {
    // Inject Ktor client or Room repository via Koin
    // private val repository: YourRepository by inject()

    private val _loginState = MutableStateFlow<AppState<String>>(AppState.Idle)
    val loginState: StateFlow<AppState<String>> = _loginState.asStateFlow()


    fun login(username: String, password: String) {
        _loginState.value = AppState.Loading
        viewModelScope.launch {
            try {
                // Simulate API/DB call with Ktor/Room
                // val result = repository.login(username, password)
                val result = "Success" // Placeholder
                _loginState.value = AppState.Success(result)
            } catch (e: Exception) {
                _loginState.value = AppState.Error(e.message ?: "Login failed")
            }
        }
    }
}