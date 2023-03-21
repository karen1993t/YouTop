package com.youtop.music_track_screen.mapper.di

import com.youtop.music_track_screen.mapper.MusicTrackDomainToUIMapper
import com.youtop.music_track_screen.mapper.MusicTrackDomainToSongItemUIMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val uiMappersKoinModule = module {
    factoryOf(::MusicTrackDomainToUIMapper)
    factoryOf(::MusicTrackDomainToSongItemUIMapper)
}