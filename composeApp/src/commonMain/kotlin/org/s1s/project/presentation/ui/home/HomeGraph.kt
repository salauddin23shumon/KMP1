package org.s1s.project.presentation.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import io.github.aakira.napier.Napier
import org.s1s.project.presentation.navigation.Graph
import org.s1s.project.presentation.navigation.Screen
import org.s1s.project.presentation.ui.Landing.LandingScreen
import org.s1s.project.presentation.ui.product.ProductDetailsScreen
import org.s1s.project.presentation.ui.product.ProductListScreen
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
        composable(Screen.ProductList.route) {
            HomeScaffold(navController) {
                ProductListScreen(
                    navController = navController,
                    onProductClick = { productId ->
                        navController.navigate(Screen.ProductDetails.withId(productId))
                    }
                )
            }
        }
        composable(
            route = Screen.ProductDetails.route, // Use the route template
            arguments = listOf(navArgument("id") { type = NavType.StringType }) // "id" should match the placeholder in your route
        ) { backStackEntry ->
            // Retrieve the argument
            val productId = backStackEntry.arguments?.getString("id")
            // It's good practice to handle the case where the ID might be null,
            // though with NavType.StringType and a non-nullable placeholder,
            // it should always be present if the route matches.
            Napier.e("$productId")
            if (productId != null) {
                HomeScaffold(navController) { ProductDetailsScreen(productId as String) }
            } else {
                Napier.e("Product ID is null")
            }
        }
//        composable(
//            route = Screen.ProductDetails.route,
//            arguments = listOf(navArgument("productId") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val id = backStackEntry.arguments?.getString("productId").orEmpty()
//            HomeScaffold(navController) { ProductDetailsScreen(id) }
//        }
        /*composable(Screen.Settings.route) {
            HomeScaffold(navController) { SettingsScreen() }
        }

        // Detail pages still share the same scaffold / appbar if you want

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

