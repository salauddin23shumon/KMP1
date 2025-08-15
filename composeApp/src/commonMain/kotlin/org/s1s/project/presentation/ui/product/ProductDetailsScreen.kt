package org.s1s.project.presentation.ui.product

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import io.ktor.websocket.Frame
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import org.s1s.project.presentation.viewModels.ProductViewModel
import org.s1s.project.utility.AppState

@Composable
fun ProductDetailsScreen(
    productId: String,
    viewModel: ProductViewModel = koinViewModel(
        parameters = { parametersOf(productId) }
    )
) {
    val state = viewModel.productDetailsState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchProductById(productId)
    }

    when (state) {
        is AppState.Error -> Frame.Text(state.errorMsg)
        is AppState.Idle -> {}
        is AppState.Loading -> CircularProgressIndicator()
        is AppState.Success -> {
            ProductDetailsUi(product = state.data)
        }
    }
}