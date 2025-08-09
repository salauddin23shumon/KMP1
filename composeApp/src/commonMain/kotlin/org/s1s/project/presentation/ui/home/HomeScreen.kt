package org.s1s.project.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.s1s.project.presentation.navigation.DrawerNavigation
import org.s1s.project.presentation.navigation.Screen
import org.s1s.project.presentation.ui.Landing.LandingScreen
import org.s1s.project.presentation.ui.Product.ProductListScreen
import org.s1s.project.presentation.ui.profile.ProfileScreen
import org.s1s.project.presentation.viewModels.HomeViewModel

/*
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
    // No need to pass the appNavController if HomeScreen handles its own internal nav
) {
    // This navController is specifically for the content within HomeScreen's NavHost
    val homeNavController = rememberNavController()
    val state by viewModel.homeState.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Ensure Landing is the default screen for the homeNavController
    LaunchedEffect(Unit) {
        // Check the homeNavController's current destination
        if (homeNavController.currentDestination?.route != Screen.Landing.route) {
            homeNavController.navigate(Screen.Landing.route) {
                // Adjust popUpTo if necessary, relative to homeNavController's graph
                // For instance, if you have a "root" for the home graph:
                // popUpTo(homeNavController.graph.startDestinationId) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerNavigation(
                homeNavController = homeNavController, // Pass the correct NavController
                onItemClicked = { // Lambda to close the drawer
                    scope.launch { drawerState.close() }
                }
            )

        },
        content = {
            NavHost(
                navController = homeNavController, // Use the dedicated homeNavController
                startDestination = Screen.Landing.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(Screen.Landing.route) {
                    LandingScreen(viewModel = viewModel)
                }
                composable(Screen.Profile.route) {
                    ProfileScreen(viewModel = viewModel)
                }
                composable(Screen.ProductList.route) {
                    // If ProductListScreen needs to navigate further within home's scope
                    ProductListScreen(navController = homeNavController)
                }
            }
        }
    )
}*/


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navController: NavHostController
) {
    val state by viewModel.homeState.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (navController.currentDestination?.route != Screen.Landing.route) {
            navController.navigate(Screen.Landing.route) {
                popUpTo(Screen.Home.route) { saveState = true }
                launchSingleTop = true
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerNavigation(
                navController = navController,
                onItemClicked = { scope.launch { drawerState.close() } }
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                // Content is rendered by the parent NavHost's composables
            }
        }
    )
}