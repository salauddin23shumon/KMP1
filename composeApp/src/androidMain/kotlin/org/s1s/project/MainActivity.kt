package org.s1s.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.s1s.project.presentation.materialTheme.DynamicAppTheme
import org.s1s.project.presentation.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicAppTheme(dynamicColor = true) {
                AppNavigation()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}