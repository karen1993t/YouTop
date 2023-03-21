package com.youtop.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

val shapes = YouTopAppRoundedCornerShape(
    shapeLarge = RoundedCornerShape(
        topStart = 40.dp,
        topEnd = 40.dp,
        bottomEnd = 0.dp,
        bottomStart = 0.dp
    ),
    shapeMedium = RoundedCornerShape(10.dp),
    shapeSmall = RoundedCornerShape(3.dp)
)