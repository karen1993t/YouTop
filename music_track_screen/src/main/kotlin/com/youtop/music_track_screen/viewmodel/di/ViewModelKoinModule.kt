package com.youtop.music_track_screen.viewmodel.di

import com.youtop.music_track_screen.viewmodel.MusicTrackViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val viewModelKoinModule = module {
    viewModelOf(::MusicTrackViewModel)
}