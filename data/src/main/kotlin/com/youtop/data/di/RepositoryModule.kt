package com.youtop.data.di

import com.youtop.data.repositoryimpl.AlbumInfoRepositoryImpl
import com.youtop.data.repositoryimpl.TrackInfoRepositoryImpl
import com.youtop.domain.repository.AlbumInfoRepository
import com.youtop.domain.repository.TrackInfoRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {
    singleOf(::AlbumInfoRepositoryImpl) { bind<AlbumInfoRepository>() }
    singleOf(::TrackInfoRepositoryImpl) { bind<TrackInfoRepository>() }
}