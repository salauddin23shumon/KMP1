package org.s1s.project.presentation.ui.Landing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.compose.viewmodel.koinViewModel
import org.s1s.project.presentation.viewModels.HomeViewModel
import org.s1s.project.utility.AppState

@Composable
fun LandingScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state = viewModel.homeState.collectAsState()
    when (state) {
        is AppState.Success<*> -> LandingUi(data = state.data as String)
        else -> Unit
    }
}