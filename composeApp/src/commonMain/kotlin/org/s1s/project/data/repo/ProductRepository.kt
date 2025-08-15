package org.s1s.project.data.repo

import org.s1s.project.data.model.Product

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun getProductById(id: String): Result<Product>
}

class ProductRepositoryImpl : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>> {
        return Result.success(
            List(10) { id ->
                    Product(id.toString(), "Product $id", "Description $id")
            }
        )
    }

    override suspend fun getProductById(id: String): Result<Product> {
        return Result.success(Product(id, "Product $id", "Description for Product $id"))
    }
}