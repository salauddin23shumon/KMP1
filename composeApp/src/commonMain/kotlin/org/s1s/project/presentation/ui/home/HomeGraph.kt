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
    // NavGraphBuilder
    navigation<GraphRoute.Home>(
        startDestination = Screen.HomeScreens.Landing
    ) {
        composable<Screen.HomeScreens.Landing> {
            HomeScaffold(navController, isTopLevelHomeScreen = true) { LandingScreen() }
        }
        composable<Screen.HomeScreens.Profile> {
            HomeScaffold(navController, isTopLevelHomeScreen = true) { ProfileScreen() }
        }
        composable<Screen.HomeScreens.ProductList> {
            HomeScaffold(navController, isTopLevelHomeScreen = true) {
                ProductListScreen(
                    navController = navController,
                    onProductClick = { productId ->
                        navController.navigate(Screen.HomeScreens.ProductDetails(id = productId))
                    }
                )
            }
        }
        composable<Screen.HomeScreens.ProductDetails> { backStackEntry ->
            val productDetails: Screen.HomeScreens.ProductDetails = backStackEntry.toRoute()
            // ProductDetails is NOT a top-level home screen in this context
            HomeScaffold(navController, isTopLevelHomeScreen = false) {
                ProductDetailsScreen(productDetails.id)
            }
        }
    }
}

