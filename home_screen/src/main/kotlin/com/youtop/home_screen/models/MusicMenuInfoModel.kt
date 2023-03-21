package com.youtop.home_screen.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.youtop.core.R
import kotlin.random.Random


@Immutable
data class MusicMenuInfoModel(
    val id: Int,
    @DrawableRes
    val resIcon: Int,
    @StringRes
    val resTitle: Int
) {
    companion object {
        fun initial() = MusicMenuInfoModel(
            id = Random.nextInt(),
            resIcon = R.drawable.ic_playlist,
            resTitle = R.string.music_menu_play_list_title
        )
    }
}
