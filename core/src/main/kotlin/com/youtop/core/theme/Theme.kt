package com.youtop.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun YouTopAppExerciseTheme(
    style: YouTopAppStyle = YouTopAppStyle.Main,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                YouTopAppStyle.Main -> baseDarkPalette
            }
        }
        false -> {
            when (style) {
                YouTopAppStyle.Main -> baseLightPalette
            }
        }
    }

    CompositionLocalProvider(
        LocalYouTopAppColors provides colors,
        LocalYouTopAppTypography provides typography,
        LocalYouTopAppRoundedCornerShape provides shapes,
        content = content
    )
}