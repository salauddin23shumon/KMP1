package org.s1s.project.presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import org.s1s.project.presentation.navigation.Screen

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        // Fake loading delay
        kotlinx.coroutines.delay(1500)
        navController.navigate(Screen.Login) {
            popUpTo(Screen.Splash) { inclusive = true }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Splash Screen", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
