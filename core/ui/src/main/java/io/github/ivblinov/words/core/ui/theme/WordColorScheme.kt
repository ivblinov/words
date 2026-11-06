package io.github.ivblinov.words.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

sealed class WordColorScheme(
    val colors: ColorScheme
) {

    data object BrownScheme : WordColorScheme(
        colors = lightColorScheme(
            // Основной акцентный цвет (кнопки, фокус в полях)
            primary = Green,
            onPrimary = Color.White,

            // Вторичный акцент
            secondary = DarkGreen,
            onSecondary = Color.White,
            secondaryContainer = Color.Cyan,

            // Третичный (например, для выделения мелких элементов)
            tertiary = LightGreen,
            onTertiary = AlmostBlack,

            // Фон самого экрана
            background = LightGreen,
            onBackground = AlmostBlack,

            // Основные поверхности (карточки)
            surface = LightBrown,
            onSurface = AlmostBlack,
            surfaceContainerLowest = LightBrown,
            surfaceContainerLow = LightBrown,
            surfaceTint = LightBrown,

            // Поверхности компонентов (поля ввода, аккордеоны)
            surfaceVariant = LightBrown,
            onSurfaceVariant = AlmostBlack.copy(alpha = 0.7f),

            // Цвет границ (outline) для полей ввода
            outline = DarkGreen.copy(alpha = 0.5f)
        )
    )
}