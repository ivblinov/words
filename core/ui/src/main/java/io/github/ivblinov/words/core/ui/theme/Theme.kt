package io.github.ivblinov.words.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun WordsTheme(
    colorScheme: WordColorScheme = WordColorScheme.BrownScheme,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = colorScheme.colors,
        typography = Typography,
        content = content
    )
}