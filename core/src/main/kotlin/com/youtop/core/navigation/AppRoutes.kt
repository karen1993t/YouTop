package com.youtop.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.youtop.core.R

sealed class AppRoutes(val route: String) {
    object SplashScreen : AppRoutes(route = "Splash Screen")
    object YouTopHomeScreen : AppRoutes(route = "You Top App Home Screen")
    object YouTopTrackScreen : AppRoutes(route = "You Top App Track Screen")

    sealed class BottomAppRoutes(
        route: String,
        @DrawableRes val image: Int,
        @StringRes val title: Int
    ) : AppRoutes(route) {
        object Home : BottomAppRoutes(
            route = "home",
            image = R.drawable.ic_home,
            title = R.string.bottom_bar_home_title
        )

        object Generies : BottomAppRoutes(
            route = "generies",
            image = R.drawable.ic_album,
            title = R.string.bottom_bar_generies_title
        )

        object Search : BottomAppRoutes(
            route = "search",
            image = R.drawable.ic_search,
            title = R.string.bottom_bar_search_title
        )

        object Favorite : BottomAppRoutes(
            route = "favorite",
            image = R.drawable.ic_favorite,
            title = R.string.bottom_bar_favorite_title
        )

        object Account : BottomAppRoutes(
            route = "account",
            image = R.drawable.ic_profilinfo,
            title = R.string.bottom_bar_account_title
        )
    }
    companion object {
        val screensBottomNav = listOf(
            BottomAppRoutes.Home,
            BottomAppRoutes.Generies,
            BottomAppRoutes.Search,
            BottomAppRoutes.Favorite,
            BottomAppRoutes.Account,
        )
    }
}
