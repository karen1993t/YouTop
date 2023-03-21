package com.youtop.home_screen.models

import androidx.compose.runtime.Immutable


@Immutable
sealed class HomeScreenSideEffect {
    object SeeAllBtnClicked : HomeScreenSideEffect()

    data class NavigateToAlbumDetailsScreen(
        val isItemClicked: Boolean,
        val data: AlbumInfoItemUIModel
    ) : HomeScreenSideEffect()

    data class ShowMessage(val message: String) : HomeScreenSideEffect()
}