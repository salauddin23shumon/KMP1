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

class HomeViewModel(private val navigationManager: NavigationManager) : ViewModel(), KoinComponent {
    // Inject Ktor client or Room repository via Koin
    // private val repository: YourRepository by inject()


    private val _homeState = MutableStateFlow<AppState<String>>(AppState.Idle)
    val homeState: StateFlow<AppState<String>> = _homeState.asStateFlow()

    fun fetchData() {
        _homeState.value = AppState.Loading
        viewModelScope.launch {
            try {
                // Simulate API/DB call with Ktor/Room
                // val result = repository.getHomeData()
                val result = "Welcome to Home"
                _homeState.value = AppState.Success(result)
            } catch (e: Exception) {
                _homeState.value = AppState.Error(e.message ?: "Failed to load data")
            }
        }
    }
}