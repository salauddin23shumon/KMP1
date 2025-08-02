package org.s1s.project.presentation.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey


class NavigationManager(val backStack: SnapshotStateList<NavKey>) { // Changed from Any to NavKey

    fun navigateToHome() {
        backStack.removeAll { it == Login }
        if (backStack.lastOrNull() != Home) { // Avoid adding if already there
            backStack.add(Home)
        }
    }

    fun navigateToLogin() {
        if (backStack.lastOrNull() != Login) { // Avoid adding if already there
            backStack.add(Login)
        }
    }
}