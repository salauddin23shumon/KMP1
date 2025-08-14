package org.s1s.project.presentation.navigation

import kotlinx.serialization.Serializable

// --- Navigation Sealed Class ---

//@Serializable
//sealed class Screen(val route: String) {
//
//    // Root-level screens
//    @Serializable data object Splash : Screen("splash")
//    @Serializable data object Signup : Screen("signup")
//
//
//    // Drawer peers inside Home
//
//
//    // No args
//    @Serializable
//    data object Login : Screen("login")
//
//    @Serializable
//    data object HomeGraph : Screen("home") // nested graph root
//
//    @Serializable
//    data object Profile : Screen("profile")
//
//    @Serializable
//    data object Landing : Screen("landing")
//
//    @Serializable
//    data object Settings : Screen("settings")
//
//    @Serializable
//    data object ProductList : Screen("productList")
//
//    // With args
//    @Serializable
//    data object ProductDetails : Screen("productDetails/{productId}") {
//        fun withArgs(productId: String) = "productDetails/$productId"
//    }
//
//    @Serializable
//    data object MoreDetails : Screen("moreDetails/{productId}") {
//        fun withArgs(productId: String) = "moreDetails/$productId"
//    }
//
//    @Serializable
//    data object OrderNow : Screen("orderNow/{productId}") {
//        fun withArgs(productId: String) = "orderNow/$productId"
//    }
//}

// Add this tiny constant (can be in Screen.kt or a new file)
object Graph {
    const val HOME = "home"
}

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

