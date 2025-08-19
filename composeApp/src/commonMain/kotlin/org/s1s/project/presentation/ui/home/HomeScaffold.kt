package org.s1s.project.presentation.ui.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import kotlinx.coroutines.launch
import org.s1s.project.presentation.navigation.DrawerContent
import org.s1s.project.presentation.navigation.GraphRoute
import org.s1s.project.presentation.navigation.Screen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScaffold(
    navController: NavController,
    isTopLevelHomeScreen: Boolean,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Get the current destination's route string
    val currentNavBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRouteString = currentNavBackStackEntry?.destination?.route
    val landingRouteString = Screen.HomeScreens.Landing::class.qualifiedName // Or your method
    val onLanding = currentRouteString == landingRouteString


    // Only intercept when we have something special to do
    val interceptBack = drawerState.isOpen || (isTopLevelHomeScreen && !onLanding)


    BackHandler(enabled = interceptBack) {
        when {
            drawerState.isOpen -> scope.launch { drawerState.close() }
            isTopLevelHomeScreen && !onLanding -> { // Only do this if it's a top-level screen
                navController.navigate(Screen.HomeScreens.Landing) {
                    popUpTo(GraphRoute.Home::class) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onDestinationClick = { route ->
                    scope.launch { drawerState.close() }
                    navController.navigate(route) {
                        popUpTo(GraphRoute.Home::class) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0)
        ) {
            Box(Modifier.fillMaxSize()) {
                content()

                // Transparent overlay appbar with a visible hamburger
                TransparentOverlayAppBar(
                    showHamburger = isTopLevelHomeScreen,
                    onHamburgerClick = { scope.launch { drawerState.open() } },
                    onBackClick = { navController.popBackStack() }, // if you want back on details
                    title = null
                )
            }
        }
    }
}


@Composable
fun TransparentOverlayAppBar(
    showHamburger: Boolean = true,
    onHamburgerClick: () -> Unit,
    onBackClick: () -> Unit,
    title: String? = "M",
    actions: @Composable RowScope.() -> Unit = {}
) {
    // A simple overlay row that respects status bar
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left icon (hamburger or back) with a subtle circular scrim so it's always visible
            ScrimmedIconButton(
                imageVector = if (showHamburger) Icons.Filled.Menu else Icons.AutoMirrored.Filled.ArrowBack,
                contentDesc = if (showHamburger) "Menu" else "Back",
                onClick = if (showHamburger) onHamburgerClick else onBackClick
            )

            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                Spacer(Modifier.width(8.dp))
            }

            // Right-side actions area
            Row(verticalAlignment = Alignment.CenterVertically, content = actions)
        }
    }
}

@Composable
private fun ScrimmedIconButton(
    imageVector: ImageVector,
    contentDesc: String?,
    onClick: () -> Unit
) {
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.35f),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDesc,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

