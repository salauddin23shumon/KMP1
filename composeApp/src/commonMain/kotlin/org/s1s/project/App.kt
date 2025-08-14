package org.s1s.project

import androidx.compose.runtime.Composable
import org.s1s.project.presentation.navigation.AppNavigation
import org.s1s.project.presentation.materialTheme.AppTheme


@Composable
fun App() {
    AppTheme {
        AppNavigation()
    }
}