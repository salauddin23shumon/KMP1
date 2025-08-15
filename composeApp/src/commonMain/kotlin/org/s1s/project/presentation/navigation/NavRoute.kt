package org.s1s.project.presentation.navigation

import kotlinx.serialization.Serializable

object Graph {
    const val HOME = "home"
}

@Serializable
sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Signup : Screen("signup")

    // Home children
    data object Landing : Screen("home/landing")
    data object Profile : Screen("home/profile")
    data object ProductList : Screen("home/productList")
    data object Settings : Screen("home/settings")
    data object ProductDetails : Screen("home/productDetails/{id}") {
        fun withId(id: String) = "home/productDetails/$id"
    }
}

