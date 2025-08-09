package org.s1s.project.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.s1s.project.domain.model.ProductDomain
import org.s1s.project.domain.usecase.ProductUseCase
import org.s1s.project.utility.AppState

class ProductViewModel(private val productUseCase: ProductUseCase) : ViewModel() {
    private val _productListState = MutableStateFlow<AppState<List<ProductDomain>>>(AppState.Idle)
    val productListState: StateFlow<AppState<List<ProductDomain>>> = _productListState.asStateFlow()

    private val _productDetailsState = MutableStateFlow<AppState<ProductDomain>>(AppState.Idle)
    val productDetailsState: StateFlow<AppState<ProductDomain>> = _productDetailsState.asStateFlow()

    fun fetchProducts() {
        _productListState.value = AppState.Loading
        viewModelScope.launch {
            productUseCase.getProducts().fold(
                onSuccess = { products ->
                    _productListState.value = AppState.Success(products)
                },
                onFailure = { error ->
                    _productListState.value = AppState.Error(error.message ?: "Failed to load products")
                }
            )
        }
    }

    fun fetchProductById(id: String) {
        _productDetailsState.value = AppState.Loading
        viewModelScope.launch {
            productUseCase.getProductById(id).fold(
                onSuccess = { product ->
                    _productDetailsState.value = AppState.Success(product)
                },
                onFailure = { error ->
                    _productDetailsState.value = AppState.Error(error.message ?: "Failed to load product")
                }
            )
        }
    }
}