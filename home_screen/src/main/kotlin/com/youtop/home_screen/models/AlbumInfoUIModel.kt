package com.youtop.home_screen.models

import androidx.compose.runtime.Immutable
import kotlin.random.Random

@Immutable
data class AlbumInfoItemUIModel(
    val id: Int,
    val albumId: String,
    val imgUrl: String,
    val albumTitle: String,
    val albumSubTitle: String,
    val albumTrackCountInfo:String,
) {
    companion object {
        fun initial() = AlbumInfoItemUIModel(
            id = Random.nextInt(),
            albumId = "",
            imgUrl = "",
            albumTitle = "Suche whore",
            albumSubTitle = "Hello",
            albumTrackCountInfo = "20 Songs"
        )
    }
}