package com.youtop.music_track_screen.models

import androidx.compose.runtime.Immutable
import kotlin.random.Random

@Immutable
internal data class SongItemUIModel(
    val id: Int,
    val songId: String,
    val artistFullName: String,
    val albumsTitle: String,
    val songArtistImgUrl: String
) {
    companion object {
        fun initial() = SongItemUIModel(
            id = Random.nextInt(),
            songId = "sdfjidji44545",
            artistFullName = "Breaking",
            albumsTitle = "Topic",
            songArtistImgUrl = ""
        )
    }
}
