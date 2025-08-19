package org.s1s.project.presentation.navigation

import kotlinx.serialization.Serializable

//object Graph {
//    const val HOME = "home"
//}

//@Serializable
//sealed class Screen(val route: String) {
//    data object Splash : Screen("splash")
//    data object Login : Screen("login")
//    data object Signup : Screen("signup")
//
//    // Home children
//    data object Landing : Screen("home/landing")
//    data object Profile : Screen("home/profile")
//    data object ProductList : Screen("home/productList")
//    data object Settings : Screen("home/settings")
//    @Serializable
//    data object ProductDetails : Screen("home/productDetails/{id}") {
//        fun withId(id: String) = "home/productDetails/$id"
//    }
//}

//import kotlinx.serialization.Serializable

@Serializable
sealed interface GraphRoute { // Renamed for clarity, can still be Graph
    @Serializable
    data object Home : GraphRoute // This is the route for the home graph

    // Other top-level graphs could go here
    // @Serializable data object Onboarding : GraphRoute
}

@Serializable
sealed interface Screen {
    @Serializable
    data object Splash : Screen

    @Serializable
    data object Login : Screen

    @Serializable
    data object Signup : Screen

    // Home Graph Screens
    @Serializable
    sealed interface HomeScreens : Screen, GraphRoute { // Marker interface implementing Home route
        @Serializable
        data object Landing : HomeScreens

        @Serializable
        data object Profile : HomeScreens

        @Serializable
        data object ProductList : HomeScreens

        @Serializable
        data object Settings : HomeScreens

        @Serializable
        data class ProductDetails(val id: String) : HomeScreens
    }
}
