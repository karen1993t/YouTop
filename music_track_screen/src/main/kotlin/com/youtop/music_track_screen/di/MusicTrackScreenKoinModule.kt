package com.youtop.music_track_screen.di

import com.youtop.music_track_screen.mapper.di.uiMappersKoinModule
import com.youtop.music_track_screen.viewmodel.di.viewModelKoinModule
import org.koin.dsl.module

val musicTrackScreenKoinModule = module {
    includes(uiMappersKoinModule, viewModelKoinModule)
}