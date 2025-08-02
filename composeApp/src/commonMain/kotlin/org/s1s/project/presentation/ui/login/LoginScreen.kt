package org.s1s.project.presentation.ui.login


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject
import org.s1s.project.presentation.navigation.NavigationManager
import org.s1s.project.presentation.viewModels.LoginViewModel
import org.s1s.project.utility.AppState

@Composable
fun LoginScreen(navigationManager: NavigationManager) {
    val viewModel: LoginViewModel = koinInject()
    val state by viewModel.loginState.collectAsState()

    when (state) {
        is AppState.Idle -> {
            LoginUi(
                onLoginClick = { username, password ->
                    viewModel.login(username, password)
                }
            )
        }
        is AppState.Loading -> {
            LoginUi(isLoading = true)
        }
        is AppState.Success -> {
            navigationManager.navigateToHome()
        }
        is AppState.Error -> {
            LoginUi(errorMsg = state.getErrorMessageOrNull())
        }
    }
}