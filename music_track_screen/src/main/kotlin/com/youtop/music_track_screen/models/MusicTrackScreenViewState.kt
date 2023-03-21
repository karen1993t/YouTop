package com.youtop.music_track_screen.models

import androidx.compose.runtime.Immutable
import com.youtop.core.models.ViewState

@Immutable
internal data class MusicTrackScreenViewState(
    val viewState: ViewState<MusicTrackUIModel>
) {
    companion object {
        fun initial() = MusicTrackScreenViewState(viewState = ViewState.Loading)
    }
}