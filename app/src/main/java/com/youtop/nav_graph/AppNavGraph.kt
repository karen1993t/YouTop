package com.youtop.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.youtop.DURATION_NAVIGATE_SCREEN
import com.youtop.TARGET_OFFSET_X_SCREEN_LEFT
import com.youtop.TARGET_OFFSET_X_SCREEN_RIGHT
import com.youtop.app.MainScreenContainer
import com.youtop.core.navigation.AppRoutes
import com.youtop.music_track_screen.MUSIC_TRACK_SCREEN_ARGUMENT_DATA_KEY
import com.youtop.music_track_screen.presentation.MusicTrackScreen
import com.youtop.splash_screen.presentation.AnimatedSplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppRoutes.SplashScreen.route
    ) {
        composable(
            route = AppRoutes.SplashScreen.route
        ) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = AppRoutes.YouTopHomeScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(DURATION_NAVIGATE_SCREEN))
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            }
        ) {
            MainScreenContainer(navController = navController)
        }

        composable(
            route = "${AppRoutes.YouTopTrackScreen.route}/{$MUSIC_TRACK_SCREEN_ARGUMENT_DATA_KEY}",
            arguments = listOf(
                navArgument(MUSIC_TRACK_SCREEN_ARGUMENT_DATA_KEY) {
                    type = NavType.StringType
                }
            ),
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            }
        ) { it ->
            val albumId = it.arguments?.getString(MUSIC_TRACK_SCREEN_ARGUMENT_DATA_KEY)
            albumId?.let {
                if (albumId.isNotBlank()) {
                    MusicTrackScreen(
                        modifier = Modifier.fillMaxSize(1f),
                        albumId = albumId,
                        navController = navController
                    )
                }
            }
        }
    }
}