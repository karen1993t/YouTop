package com.youtop.home_screen.di

import com.youtop.home_screen.mapper.di.uiMapperKoinModule
import com.youtop.home_screen.viewmodel.di.homeScreenViewModelKoinModule
import org.koin.dsl.module

val homeScreenKoinModule = module {
    includes(homeScreenViewModelKoinModule, uiMapperKoinModule)
}