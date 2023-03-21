package com.youtop.music_track_screen.models

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class MusicTrackScreenSideEffect {
    object NavigateToBackStack : MusicTrackScreenSideEffect()
    data class ShowMessage(val message: String,val isErrorMessage:Boolean) : MusicTrackScreenSideEffect()
}
