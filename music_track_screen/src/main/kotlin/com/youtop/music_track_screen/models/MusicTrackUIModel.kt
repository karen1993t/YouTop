package com.youtop.music_track_screen.models

import androidx.compose.runtime.Immutable

@Immutable
internal data class MusicTrackUIModel(
    val id: Int,
    val trackId: String,
    val artistFullName: String,
    val subTitle: String,
    val profileImageUrl: String,
    val songId: String,
    val songUrl: String,
    val songName: String,
    val songDuration: Double,
    val trackLikeCountInfo: String,
)