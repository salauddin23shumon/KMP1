package org.s1s.project.presentation.navigation



import kotlinx.serialization.Serializable



// --- Navigation Sealed Class ---
@Serializable
sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object Landing : Screen("landing")
    data object Settings : Screen("settings")
    data object ProductList : Screen("productList")
    data class ProductDetails(val id: String) : Screen("productDetails/{id}") {
        fun createRoute(id: String) = "productDetails/$id"
    }
    data object MoreDetails : Screen("moreDetails")
    data object OrderNow : Screen("orderNow")
}
