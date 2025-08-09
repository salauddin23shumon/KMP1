package org.s1s.project.data.repo

import org.s1s.project.data.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
}

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(username: String, password: String): Result<User> {
        return try {
            if (username.isNotEmpty() && password.isNotEmpty()) {
                Result.success(User(username, "mock_token"))
            } else {
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}