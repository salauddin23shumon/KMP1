package org.s1s.project.presentation.ui.home


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.s1s.project.presentation.navigation.materialTheme.AppTheme

@Composable
fun HomeUi(
    data: String = "",
    isLoading: Boolean = false,
    errorMsg: String? = null,
    onHamburgerClick: () -> Unit
) {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = onHamburgerClick,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Open drawer"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else if (errorMsg != null) {
                    Text(text = errorMsg, color = MaterialTheme.colorScheme.error)
                } else {
                    Text(text = data, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}