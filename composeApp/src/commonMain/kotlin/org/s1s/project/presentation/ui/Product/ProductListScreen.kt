package org.s1s.project.presentation.ui.Product

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import io.ktor.websocket.Frame
import org.koin.compose.viewmodel.koinViewModel
import org.s1s.project.presentation.navigation.Screen
import org.s1s.project.presentation.viewModels.ProductViewModel
import org.s1s.project.utility.AppState

@Composable
fun ProductListScreen(
    viewModel: ProductViewModel = koinViewModel(),
    navController: NavHostController
) {
    val state = viewModel.productListState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

//    when (state) {
//        is AppState.Success -> ProductListUi(
//            products = state.data,
//            onProductClick = { id ->
//                navController.navigate(Screen.ProductDetails.createRoute(id))
//            }
//        )
//        is AppState.Loading -> CircularProgressIndicator()
//        is AppState.Error -> Frame.Text(state.errorMsg)
//        else -> Unit
//    }
}