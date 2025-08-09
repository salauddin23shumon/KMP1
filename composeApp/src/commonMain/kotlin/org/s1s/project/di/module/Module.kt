package org.s1s.project.di.module


import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.s1s.project.data.repo.AuthRepository
import org.s1s.project.data.repo.AuthRepositoryImpl
import org.s1s.project.data.repo.ProductRepository
import org.s1s.project.data.repo.ProductRepositoryImpl
import org.s1s.project.domain.usecase.LoginUseCase
import org.s1s.project.domain.usecase.ProductUseCase
import org.s1s.project.presentation.viewModels.HomeViewModel
import org.s1s.project.presentation.viewModels.LoginViewModel
import org.s1s.project.presentation.viewModels.ProductViewModel


val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }
    single<ProductRepository> { ProductRepositoryImpl() }
    factory { LoginUseCase(get()) }
    factory { ProductUseCase(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { ProductViewModel(get()) }
}