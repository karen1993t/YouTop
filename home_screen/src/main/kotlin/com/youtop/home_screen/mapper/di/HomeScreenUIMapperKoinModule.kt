package com.youtop.home_screen.mapper.di

import com.youtop.home_screen.mapper.AlbumInfoDomainToUIMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val uiMapperKoinModule = module {
    factoryOf(::AlbumInfoDomainToUIMapper)
}