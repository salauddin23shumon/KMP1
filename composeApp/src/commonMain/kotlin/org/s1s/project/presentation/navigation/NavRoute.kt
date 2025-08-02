package org.s1s.project.presentation.navigation


import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.rememberNavBackStack


@Serializable
data object Login : NavKey

@Serializable
data object Home : NavKey

data class DrawerItem(val key: NavKey, val label: String)

val drawerItems = listOf(
    DrawerItem(Home, "Home"),
    DrawerItem(Login, "Login")
)

/**
 * Remembers and manages the navigation backstack for the application.
 *
 * @param initialScreen The initial screen to display when the app starts.
 * @return A SnapshotStateList representing the navigation backstack.
 */
@Composable
fun rememberAppNavigationState(initialScreen: NavKey = Login): SnapshotStateList<NavKey> {
    // We specify NavKey as the type for the backstack for better type safety.
    return rememberNavBackStack(initialScreen)
}
