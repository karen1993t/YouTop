package com.youtop.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.youtop.core.navigation.AppRoutes
import com.youtop.core.theme.YouTopAppTheme

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(containerColor = YouTopAppTheme.colors.primaryBackground) {

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        AppRoutes.screensBottomNav.forEach { navItem ->
            val isSelected = currentRoute == navItem.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.image),
                        contentDescription = stringResource(id = navItem.title)
                    )
                },
                label = {
                    Text(
                        style = YouTopAppTheme.typography.titleSmall,
                        text = stringResource(id = navItem.title),
                        color = YouTopAppTheme.colors.bottomAppBarTextColor,
                        textAlign = TextAlign.Center
                    )
                },
                alwaysShowLabel = true,
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor =
//                )
            )

        }
    }


}
