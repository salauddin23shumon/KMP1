package org.s1s.project.presentation.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey


class NavigationManager(val backStack: SnapshotStateList<NavKey>) {

    fun navigateTo(screen: Screen) {
        // More robust navigation: clear back stack up to the new screen if it exists,
        // or add it. Prevent duplicate additions at the top.
        if (backStack.lastOrNull() == screen) {
            return // Already on this screen
        }

        // If the screen exists in the backstack, pop until it's at the top
        val existingIndex = backStack.indexOf(screen)
        if (existingIndex != -1) {
            while (backStack.size > existingIndex + 1) {
                backStack.removeLast()
            }
        } else {
            // If it's a new screen, add it.
            // You might want to clear specific screens, like Login, when navigating to Home.
            if (screen == Screen.Home && backStack.contains(Screen.Login)) {
                backStack.removeAll { it == Screen.Login }
            }
            backStack.add(screen)
        }
    }

    fun navigateBack() {
        if (backStack.size > 1) { // Ensure there's something to go back to
            backStack.removeLast()
        }
        // Else, handle cannot go back (e.g., close app or specific behavior)
    }

    // Specific navigation actions (optional, can also use navigateTo(Screen.Home))
    fun navigateToHome() {
        navigateTo(Screen.Home)
    }

    fun navigateToLogin() {
        // Typically, when navigating to Login, you might want to clear the Home screen or the whole stack
        backStack.clear()
        backStack.add(Screen.Login)
    }
}