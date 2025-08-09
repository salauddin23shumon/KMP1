package org.s1s.project.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawerNavigation(
    navController: NavHostController, // Use the top-level appNavController
    onItemClicked: () -> Unit // Lambda to close the drawer
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        DrawerItem("Profile", Screen.Profile.route, navController, onItemClicked)
        DrawerItem("Landing", Screen.Landing.route, navController, onItemClicked)
        DrawerItem("Settings", Screen.Settings.route, navController, onItemClicked)
        DrawerItem("Product List", Screen.ProductList.route, navController, onItemClicked)
    }
}

@Composable
fun DrawerItem(
    label: String,
    route: String,
    navController: NavHostController, // Use the top-level appNavController
    onItemClicked: () -> Unit
) {
    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(route) {
                    // Pop up to the Home route to keep the Home section as the root of this navigation
                    popUpTo(Screen.Home.route) {
                        saveState = true // Preserve state of screens popped off
                    }
                    launchSingleTop = true // Avoid multiple copies of the same screen
                    restoreState = true // Restore state when navigating back
                }
                onItemClicked() // Close the drawer
            }
            .padding(16.dp)
    )
}
