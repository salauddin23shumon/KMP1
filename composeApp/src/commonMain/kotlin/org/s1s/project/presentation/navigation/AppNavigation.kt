package org.s1s.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.s1s.project.presentation.ui.home.homeGraph
import org.s1s.project.presentation.ui.login.LoginScreen
import org.s1s.project.presentation.ui.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Root screens (no appbar/drawer)
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController= navController) }
//        composable(Screen.Signup.route) { SignupScreen(navController) }


        homeGraph(navController)
    }
}


