package com.youtop.home_screen.models

import androidx.compose.runtime.Immutable
import com.youtop.core.models.ViewState

@Immutable
data class HomeScreenViewState(
    val viewState: ViewState<List<AlbumInfoItemUIModel>>
) {
    companion object {
        fun initial() = HomeScreenViewState(viewState = ViewState.Loading)
    }
}