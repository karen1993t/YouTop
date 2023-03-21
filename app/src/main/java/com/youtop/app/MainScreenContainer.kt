package com.youtop.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.youtop.bottombar.BottomNavigationBar
import com.youtop.bottombar.BottomBarNavGraph
import com.youtop.core.R
import com.youtop.presentation.AppTopBar
import com.youtop.core.theme.YouTopAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreenContainer(navController: NavController) {
    val bottomNavController = rememberAnimatedNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.home_screen_toolbar_title),
                actions = {}
            )
        },
        content = {
            Box(
                modifier = Modifier.padding(
                    PaddingValues(
                        0.dp,
                        0.dp,
                        0.dp,
                        it.calculateBottomPadding()
                    )
                )
            ) {
                BottomBarNavGraph(
                    navController = navController,
                    bottomNavController = bottomNavController
                )
            }
        },
        bottomBar = { BottomNavigationBar(navController = bottomNavController) },
        backgroundColor = YouTopAppTheme.colors.primaryBackground
    )
}