package org.s1s.project.presentation.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.s1s.project.presentation.navigation.Graph
import org.s1s.project.presentation.navigation.Screen
import org.s1s.project.presentation.ui.Landing.LandingScreen
import org.s1s.project.presentation.ui.profile.ProfileScreen


fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        route = Graph.HOME,
        startDestination = Screen.Landing.route
    ) {
        // Drawer peers (all wrapped by HomeScaffold)
        composable(Screen.Landing.route) {
            HomeScaffold(navController) { LandingScreen() }
        }
        composable(Screen.Profile.route) {
            HomeScaffold(navController) { ProfileScreen() }
        }
        /*composable(Screen.ProductList.route) {
            HomeScaffold(navController) {
                ProductListScreen(
                    onProductClick = { id ->
                        navController.navigate(Screen.ProductDetails.withArgs(id))
                    }
                )
            }
        }
        composable(Screen.Settings.route) {
            HomeScaffold(navController) { SettingsScreen() }
        }

        // Detail pages still share the same scaffold / appbar if you want
        composable(
            route = Screen.ProductDetails.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("productId").orEmpty()
            HomeScaffold(navController) { ProductDetailsScreen(id) }
        }
        composable(
            route = Screen.MoreDetails.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("productId").orEmpty()
            HomeScaffold(navController) { MoreDetailsScreen(id) }
        }
        composable(
            route = Screen.OrderNow.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("productId").orEmpty()
            HomeScaffold(navController) { OrderNowScreen(id) }
        }*/
    }
}

