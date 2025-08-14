package org.s1s.project.presentation.materialTheme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import org.s1s.project.presentation.navigation.materialTheme.Typography
import org.s1s.project.presentation.navigation.materialTheme.primary
import org.s1s.project.presentation.navigation.materialTheme.secondary
import org.s1s.project.presentation.navigation.materialTheme.tertiary

private val LightColorScheme = lightColorScheme(
    primary = primary,
    secondary = secondary,
    tertiary = tertiary
)


@Composable
fun AppTheme(
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}