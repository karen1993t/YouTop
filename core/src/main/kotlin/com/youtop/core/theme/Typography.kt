package com.youtop.core.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.youtop.core.R


val typography = YouTopAppTypography(
    titleLarge = TextStyle(
        fontSize = 24.sp,
        letterSpacing = (-0.75).sp,
        lineHeight = 30.36.sp,
        fontFamily = FontFamily(Font(R.font.font_circular_std_black)),
        fontWeight = FontWeight.W900
    ),
    titleMediumRegular = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.09.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
        fontWeight = FontWeight.W400
    ),
    titleMediumBold = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.09.sp,
        letterSpacing = (-0.17).sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold)),
        fontWeight = FontWeight.W700
    ),

    titleSmall = TextStyle(
        fontSize = 10.sp,
        letterSpacing = (-0.2).sp,
        lineHeight = 11.93.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_medium)),
        fontWeight = FontWeight.W500
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.09.sp,
        letterSpacing = (-0.1).sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_medium)),
        fontWeight = FontWeight.W500
    ),

    labelLarge = TextStyle(
        fontSize = 13.sp,
        lineHeight = 15.51.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
        fontWeight = FontWeight.W400
    ),
    labelMedium = TextStyle(
        fontSize = 10.sp,
        letterSpacing = (-0.42).sp,
        lineHeight = 11.93.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
        fontWeight = FontWeight.W400
    ),
    labelSmall = TextStyle(
        fontSize = 8.sp,
        letterSpacing = (-0.17).sp,
        lineHeight = 9.55.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_medium)),
        fontWeight = FontWeight.W500
    ),
    musicMenuItem = TextStyle(
        fontSize = 15.sp,
        lineHeight = 17.9.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_medium)),
        fontWeight = FontWeight.W500
    )
)