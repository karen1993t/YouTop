package com.youtop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppStyle
import com.youtop.nav_graph.AppNavGraph


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDarkModeValue = isSystemInDarkTheme()
            val currentStyle by remember { mutableStateOf(YouTopAppStyle.Main) }
            val isDarkMode by remember { mutableStateOf(isDarkModeValue) }
            val systemUiController = rememberSystemUiController()

            systemUiController.setSystemBarsColor(color = Color.White)

            YouTopAppExerciseTheme(darkTheme = isDarkMode, style = currentStyle) {
                CompositionLocalProvider {
                    val animatedNavController = rememberAnimatedNavController()
                    AppNavGraph(
                        navController = animatedNavController
                    )
                }
            }

        }
    }
}