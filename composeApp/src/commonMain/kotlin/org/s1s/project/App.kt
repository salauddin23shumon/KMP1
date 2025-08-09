package org.s1s.project

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import org.s1s.project.presentation.navigation.AppNavigation
import org.s1s.project.presentation.navigation.materialTheme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AppTheme {
        AppNavigation()
    }
}