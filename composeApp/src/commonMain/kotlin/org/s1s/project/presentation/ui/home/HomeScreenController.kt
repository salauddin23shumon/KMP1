package org.s1s.project.presentation.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject
import org.s1s.project.presentation.viewModels.HomeViewModel
import org.s1s.project.utility.AppState

@Composable
fun HomeScreen(
    onHamburgerClick: () -> Unit
) {
    val viewModel: HomeViewModel = koinInject()
    val state by viewModel.homeState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    when (state) {
        is AppState.Idle -> {
            HomeUi(onHamburgerClick = onHamburgerClick)
        }
        is AppState.Loading -> {
            HomeUi(isLoading = true, onHamburgerClick = onHamburgerClick)
        }
        is AppState.Success -> {
            HomeUi(
                data = state.getSuccessDataOrNull() ?: "",
                onHamburgerClick = onHamburgerClick
            )
        }
        is AppState.Error -> {
            HomeUi(
                errorMsg = state.getErrorMessageOrNull(),
                onHamburgerClick = onHamburgerClick
            )
        }
    }
}