package org.s1s.project.presentation.ui.login


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.s1s.project.presentation.navigation.Screen
import org.s1s.project.presentation.viewModels.LoginViewModel
import org.s1s.project.utility.AppState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavHostController
) {
    val username = remember { mutableStateOf("user") }
    val password = remember { mutableStateOf("mock_toekn") }
    val state by viewModel.loginState.collectAsState()

    // Handle navigation and error feedback
    LaunchedEffect(state) {
        when (state) {
            is AppState.Success -> {
                navController.navigate(Screen.HomeScreens.Landing) {
                    popUpTo(Screen.Login) { inclusive = true }
                    launchSingleTop = true
                }
            }
            is AppState.Error -> {
                // Show toast or snackbar with error
                // For debugging, you can log or display the error
                println("Login Error: ${(state as AppState.Error).errorMsg}")
            }
            else -> Unit
        }
    }

    LoginUi(
        username = username.value,
        password = password.value,
        onUsernameChange = { username.value = it },
        onPasswordChange = { password.value = it },
        onLoginClick = { viewModel.login(username.value, password.value) },
        isLoading = state is AppState.Loading
    )
}