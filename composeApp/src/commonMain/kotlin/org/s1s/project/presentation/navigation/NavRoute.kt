package org.s1s.project.presentation.navigation


import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.rememberNavBackStack


// --- Navigation Sealed Class ---
@Serializable
sealed class Screen : NavKey {
    @Serializable
    data object Login : Screen()

    @Serializable
    data object Home : Screen()
    // Add other screens here if needed
}

// --- Drawer Item Data Class ---
data class DrawerItem(val key: NavKey, val label: String)

// --- Drawer Items List ---
// It's good practice to define this at a readable scope,
// potentially outside the Composable if it's static.
val drawerItems = listOf(
    DrawerItem(Screen.Home, "Home"),
    // DrawerItem(Screen.Login, "Login") // Usually, Login is not in the main drawer once logged in
)

/**
 * Remembers and manages the navigation backstack for the application.
 *
 * @param initialScreen The initial screen to display when the app starts.
 * @return A SnapshotStateList representing the navigation backstack.
 */
@Composable
fun rememberAppNavigationState(initialScreen: NavKey = Screen.Login): SnapshotStateList<NavKey> {
    // We specify NavKey as the type for the backstack for better type safety.
    return rememberNavBackStack(initialScreen)
}
