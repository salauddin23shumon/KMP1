package org.s1s.project.domain.usecase

import org.s1s.project.data.repo.AuthRepository
import org.s1s.project.domain.model.UserDomain

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<UserDomain> {
        return authRepository.login(username, password).map { user ->
            UserDomain(user.username, user.token)
        }
    }
}