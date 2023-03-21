package com.youtop.splash_screen.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.youtop.core.navigation.AppRoutes
import com.youtop.splash_screen.constants.SplashScreenConstants.DEFAULT_SPLASH_SCREEN_ALPHA
import com.youtop.splash_screen.constants.SplashScreenConstants.SHOW_HOME_SCREEN_TIME_TO_MILLISECONDS
import com.youtop.splash_screen.constants.SplashScreenConstants.SHOW_SPLASH_SCREEN_TIME_TO_MILLISECONDS
import com.youtop.splash_screen.constants.SplashScreenConstants.SPLASH_SCREEN_HIDE
import kotlinx.coroutines.delay


@Composable
fun AnimatedSplashScreen(navController: NavHostController) {

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) DEFAULT_SPLASH_SCREEN_ALPHA else SPLASH_SCREEN_HIDE,
        animationSpec = tween(
            durationMillis = SHOW_SPLASH_SCREEN_TIME_TO_MILLISECONDS
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(timeMillis = SHOW_HOME_SCREEN_TIME_TO_MILLISECONDS)
        navController.apply {
            popBackStack()
            navigate(AppRoutes.YouTopHomeScreen.route)
        }
    }
    SplashScreen(alpha = alphaAnim.value)
}




