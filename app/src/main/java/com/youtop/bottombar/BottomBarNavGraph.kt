package com.youtop.bottombar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.youtop.core.navigation.AppRoutes
import com.youtop.home_screen.presentation.home_screen.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomBarNavGraph(
    bottomNavController: NavHostController,
    navController: NavController,
) {
    AnimatedNavHost(
        modifier = Modifier.fillMaxWidth(),
        navController = bottomNavController,
        startDestination = AppRoutes.BottomAppRoutes.Home.route
    ) {
        composable(
            route = AppRoutes.BottomAppRoutes.Home.route,
            enterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            }) {
            HomeScreen(modifier = Modifier.fillMaxSize(), navController = navController)
        }
        composable(
            route = AppRoutes.BottomAppRoutes.Generies.route,
            enterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            }) {
        }
        composable(
            route = AppRoutes.BottomAppRoutes.Search.route,
            enterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            }) {
        }
        composable(
            route = AppRoutes.BottomAppRoutes.Favorite.route,
            enterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            }) {
        }
        composable(
            route = AppRoutes.BottomAppRoutes.Account.route,
            enterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_LEFT }
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(DURATION_NAVIGATE_SCREEN)) { TARGET_OFFSET_X_SCREEN_RIGHT }
            }) {
        }
    }
}