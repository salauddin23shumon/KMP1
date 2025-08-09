package org.s1s.project.domain.usecase

import org.s1s.project.data.repo.ProductRepository
import org.s1s.project.domain.model.ProductDomain

class ProductUseCase(private val productRepository: ProductRepository) {
    suspend fun getProducts(): Result<List<ProductDomain>> {
        return productRepository.getProducts().map { products ->
            products.map { ProductDomain(it.id, it.name, it.description) }
        }
    }

    suspend fun getProductById(id: String): Result<ProductDomain> {
        return productRepository.getProductById(id).map {
            ProductDomain(it.id, it.name, it.description)
        }
    }
}