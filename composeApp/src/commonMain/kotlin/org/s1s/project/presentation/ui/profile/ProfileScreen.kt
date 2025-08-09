package org.s1s.project.presentation.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.compose.viewmodel.koinViewModel
import org.s1s.project.presentation.viewModels.HomeViewModel
import org.s1s.project.utility.AppState

@Composable
fun ProfileScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state = viewModel.homeState.collectAsState()
    when (state) {
        is AppState.Success<*> -> ProfileUi(data = "Profile Page")
        else -> Unit
    }
}