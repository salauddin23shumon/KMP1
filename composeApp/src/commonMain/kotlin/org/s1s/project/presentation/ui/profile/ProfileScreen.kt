package org.s1s.project.presentation.ui.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import io.github.aakira.napier.Napier
import org.koin.compose.viewmodel.koinViewModel
import org.s1s.project.presentation.viewModels.HomeViewModel
import org.s1s.project.utility.AppState
import org.s1s.project.utility.AppState.Success

@Composable
fun ProfileScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state = viewModel.homeState.collectAsState() // Use 'by' delegate for cleaner access


    when (state.value) {
        is Success<*> -> {
            Napier.i("Success")
            ProfileUi(data = (state.value as Success<Any?>).data as String)
        }
        is AppState.Loading -> {
            Text("Loading profile...") // Handle loading state
        }
        is AppState.Error -> {
            Text("Error: ${(state as AppState.Error)}") // Handle error state
        }
        else -> {
            Text("Profile not available (Unhandled state: $state)")
        }
    }
}
