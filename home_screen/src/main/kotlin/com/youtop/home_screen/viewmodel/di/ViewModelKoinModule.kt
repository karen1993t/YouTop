package com.youtop.home_screen.viewmodel.di

import com.youtop.home_screen.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val homeScreenViewModelKoinModule = module {
    viewModelOf(::HomeScreenViewModel)
}