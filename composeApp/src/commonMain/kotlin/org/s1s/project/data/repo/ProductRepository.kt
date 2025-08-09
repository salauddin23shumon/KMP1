package org.s1s.project.data.repo

import org.s1s.project.data.model.Product

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun getProductById(id: String): Result<Product>
}

class ProductRepositoryImpl : ProductRepository {
    override suspend fun getProducts(): Result<List<Product>> {
        return Result.success(
            listOf(
                Product("1", "Product 1", "Description 1"),
                Product("2", "Product 2", "Description 2")
            )
        )
    }

    override suspend fun getProductById(id: String): Result<Product> {
        return Result.success(Product(id, "Product $id", "Description for Product $id"))
    }
}