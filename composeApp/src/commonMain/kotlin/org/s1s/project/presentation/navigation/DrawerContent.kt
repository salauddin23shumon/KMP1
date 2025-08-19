package org.s1s.project.presentation.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DrawerContent(onDestinationClick: (Screen) -> Unit) {
    ModalDrawerSheet {
        Spacer(Modifier.height(24.dp))
        NavigationDrawerItem(
            label = { Text("Landing") },
            selected = false,
            onClick = { onDestinationClick(Screen.HomeScreens.Landing) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Profile") },
            selected = false,
            onClick = { onDestinationClick(Screen.HomeScreens.Profile) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Products") },
            selected = false,
            onClick = { onDestinationClick(Screen.HomeScreens.ProductList) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = false,
            onClick = { onDestinationClick(Screen.HomeScreens.Settings) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}


