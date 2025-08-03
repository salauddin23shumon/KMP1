package org.s1s.project.di.module

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import org.s1s.project.presentation.navigation.NavigationManager
import org.koin.dsl.module
import org.s1s.project.presentation.navigation.Screen
import org.s1s.project.presentation.viewModels.HomeViewModel
import org.s1s.project.presentation.viewModels.LoginViewModel


val appModule = module {
    single { mutableStateListOf<NavKey>(Screen.Login) }
    single { NavigationManager(get()) }
    factory { LoginViewModel(get<NavigationManager>()) }
    factory { HomeViewModel(get<NavigationManager>()) }
    // Add Ktor and Room dependencies as needed
}