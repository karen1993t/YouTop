package com.youtop.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class YouTopAppColors(
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val shadow: Color,
    val primaryTopAppBarTextColor: Color,
    val secondaryTopAppBarTextColor: Color,
    val primaryTitle: Color,
    val secondaryTitle: Color,
    val thirdTitle: Color,
    val primaryLabel: Color,
    val secondaryLabel: Color,
    val bottomAppBarTextColor: Color,
    val buttonPrimaryText: Color,
    val buttonPrimaryBorderStrokeColor: Color,
    val buttonPrimaryBackGround: Color,
    val primaryStatusBarColor: Color,
    val secondaryStatusBarColor: Color,
    val lineColor:Color,
    val errorColor:Color

)

data class YouTopAppTypography(
    val titleLarge: TextStyle,
    val titleMediumRegular: TextStyle,
    val titleMediumBold: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
    val musicMenuItem: TextStyle
)

data class YouTopAppRoundedCornerShape(
    val shapeLarge: RoundedCornerShape,
    val shapeMedium: RoundedCornerShape,
    val shapeSmall: RoundedCornerShape
)

object YouTopAppTheme {
    val colors: YouTopAppColors
        @Composable
        get() = LocalYouTopAppColors.current

    val typography: YouTopAppTypography
        @Composable
        get() = LocalYouTopAppTypography.current

    val roundedCornerShape: YouTopAppRoundedCornerShape
        @Composable
        get() = LocalYouTopAppRoundedCornerShape.current
}

val LocalYouTopAppColors = staticCompositionLocalOf<YouTopAppColors> {
    error("No colors provided")
}
val LocalYouTopAppTypography = staticCompositionLocalOf<YouTopAppTypography> {
    error("No font provided")
}

val LocalYouTopAppRoundedCornerShape =
    staticCompositionLocalOf<YouTopAppRoundedCornerShape> {
        error("No shape provided")
    }

enum class YouTopAppStyle {
    Main
}

