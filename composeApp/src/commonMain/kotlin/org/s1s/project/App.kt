package org.s1s.project

import androidx.compose.foundation.layout.padding
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
import org.s1s.project.presentation.navigation.Home
import org.s1s.project.presentation.navigation.Login
import org.s1s.project.presentation.navigation.NavigationManager
import org.s1s.project.presentation.navigation.drawerItems
import org.s1s.project.presentation.navigation.materialTheme.AppTheme
import org.s1s.project.presentation.ui.ScreenWrapper
import org.s1s.project.presentation.ui.home.HomeScreen
import org.s1s.project.presentation.ui.login.LoginScreen

@Composable
fun App() {
    AppTheme {
        val navigationManager: NavigationManager = koinInject()
        val backStack = navigationManager.backStack
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text("Menu", modifier = Modifier.padding(16.dp))
                    drawerItems.filter { it.key != Login }.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(item.label) },
                            selected = backStack.lastOrNull() == item.key,
                            onClick = {
                                scope.launch { drawerState.close() }
                                when (item.key) {
                                    Home -> navigationManager.navigateToHome()
                                    Login -> navigationManager.navigateToLogin()
                                }
                            }
                        )
                    }
                }
            }
        ) {
            ScreenWrapper(
                currentKey = backStack.lastOrNull(),
                onHamburgerClick = { scope.launch { drawerState.open() } }
            ) {
                NavDisplay(
                    backStack = backStack,
                    entryProvider = entryProvider {
                        {
                            entry<Login> { LoginScreen(navigationManager) }
                            entry<Home> { HomeScreen { scope.launch { drawerState.open() } } }
                        }
                    }
                )
            }
        }
    }
}