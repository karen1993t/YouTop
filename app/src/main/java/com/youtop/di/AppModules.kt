package com.youtop.di

import com.youtop.core.di.coreKoinModules
import com.youtop.data.di.dataKoinModule
import com.youtop.domain.di.domainKoinModule
import com.youtop.home_screen.di.homeScreenKoinModule
import com.youtop.music_track_screen.di.musicTrackScreenKoinModule
import org.koin.dsl.module


val appKoinModule = module {
    includes(
        coreKoinModules,
        dataKoinModule,
        domainKoinModule,
        homeScreenKoinModule,
        musicTrackScreenKoinModule
    )
}