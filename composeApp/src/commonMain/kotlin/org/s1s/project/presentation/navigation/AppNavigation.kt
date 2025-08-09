package org.s1s.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import org.s1s.project.presentation.ui.Landing.LandingScreen
import org.s1s.project.presentation.ui.Product.ProductListScreen
import org.s1s.project.presentation.ui.home.HomeScreen
import org.s1s.project.presentation.ui.login.LoginScreen
import org.s1s.project.presentation.ui.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()
    NavHost(navController = appNavController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController = appNavController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = appNavController)
        }
        composable(Screen.Landing.route) {
            LandingScreen(viewModel = koinViewModel())
        }
        composable(Screen.Profile.route) {
            ProfileScreen(viewModel = koinViewModel())
        }
        composable(Screen.ProductList.route) {
            ProductListScreen(navController = appNavController)
        }
    }
}