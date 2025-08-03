package org.s1s.project

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.s1s.project.presentation.navigation.NavigationManager
import org.s1s.project.presentation.navigation.Screen
import org.s1s.project.presentation.navigation.drawerItems
import org.s1s.project.presentation.navigation.materialTheme.AppTheme
import org.s1s.project.presentation.ui.ScreenWrapper
import org.s1s.project.presentation.ui.home.HomeScreen
import org.s1s.project.presentation.ui.login.LoginScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    // Assuming AppTheme is defined elsewhere
    // AppTheme {
    val navigationManager: NavigationManager = koinInject()
    // Use a rememberUpdatedState for backStack to ensure NavDisplay recomposes correctly
    // when the backStack instance from Koin changes, though with single, it shouldn't.
    // However, directly using navigationManager.backStack is usually fine.
    val backStack = navigationManager.backStack
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // It's important that the initial state of the backStack (e.g., Screen.Login)
    // has a corresponding entry in the entryProvider.

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                Divider()
                // Filter out Login screen from drawer, or any screen you don't want there.
                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item.label) },
                        selected = backStack.lastOrNull() == item.key, // Ensure item.key is a Screen instance
                        onClick = {
                            scope.launch { drawerState.close() }
                            // Ensure item.key is cast or checked to be a Screen type if necessary
                            // though with current DrawerItem setup, it's already a NavKey (which Screen is)
                            val targetScreen = item.key
                            if (targetScreen is Screen) { // Type check for safety
                                navigationManager.navigateTo(targetScreen)
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        // ScreenWrapper is not defined in your snippet, assuming it's a custom composable
        // that likely provides a Scaffold or similar structure.
        // For this example, I'll provide a basic structure.
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(backStack.lastOrNull()?.toString() ?: "App") },
                    navigationIcon = {
                        // Show hamburger only if not on login, or if drawer is main navigation
                        if (backStack.lastOrNull() != Screen.Login && drawerItems.isNotEmpty()) {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Open Drawer")
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavDisplay(
                modifier = Modifier.padding(paddingValues),
                backStack = backStack, // This is the SnapshotStateList<NavKey>
                // The entryProvider maps a NavKey from the backStack to its Composable content.
                // The keys (Screen.Login, Screen.Home) must be the exact objects
                // or objects that are .equals() to those in the backStack.
                // Since Screen.Login and Screen.Home are `data object`s, they are singletons,
                // so direct comparison works.
                entryProvider = entryProvider {
                    // The structure here is crucial for `navigation3.runtime`
                    entry<Screen.Login> { // NavKey is Screen.Login object
                        LoginScreen(navigationManager)
                    }
                    entry<Screen.Home> { // NavKey is Screen.Home object
                        HomeScreen {
                            scope.launch { drawerState.open() }
                        }
                    }
                    // Add entries for any other Screen objects you define
                }
            )
        }
    }
}