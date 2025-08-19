package org.s1s.project.presentation.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import org.s1s.project.presentation.navigation.GraphRoute
import org.s1s.project.presentation.navigation.Screen
import org.s1s.project.presentation.ui.Landing.LandingScreen
import org.s1s.project.presentation.ui.product.ProductDetailsScreen
import org.s1s.project.presentation.ui.product.ProductListScreen
import org.s1s.project.presentation.ui.profile.ProfileScreen


fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation<GraphRoute.Home>(
//        route = HomeGraphRoute.route,
        startDestination = Screen.HomeScreens.Landing
    ) {
        // Drawer peers (all wrapped by HomeScaffold)
        composable<Screen.HomeScreens.Landing> {
            HomeScaffold(navController) { LandingScreen() }
        }
        composable<Screen.HomeScreens.Profile> {
            HomeScaffold(navController) { ProfileScreen() }
        }
        composable<Screen.HomeScreens.ProductList> {
            HomeScaffold(navController) {
                ProductListScreen(
                    navController = navController,
                    onProductClick = { productId ->
                        navController.navigate(Screen.HomeScreens.ProductDetails(id = productId))
                    }
                )
            }
        }
//        composable<Screen.HomeScreens.Settings> {
//            HomeScaffold(navController) { SettingsScreen() }
//        }
        // Product Details now uses the type-safe class
        composable<Screen.HomeScreens.ProductDetails> { backStackEntry ->
            // Retrieve the type-safe object from the back stack entry
            val productDetails: Screen.HomeScreens.ProductDetails = backStackEntry.toRoute()
            HomeScaffold(navController) { ProductDetailsScreen(productDetails.id) }
        }
    }
}

