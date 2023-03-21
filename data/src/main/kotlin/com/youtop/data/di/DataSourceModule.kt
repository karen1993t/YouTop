package com.youtop.data.di

import com.youtop.data.album_info.datasource.remote.AlbumInfoRemoteDataSource
import com.youtop.data.album_info.datasource.remote.AlbumInfoRemoteDataSourceImpl
import com.youtop.data.track_info.datasource.remote.TrackInfoRemoteDataSource
import com.youtop.data.track_info.datasource.remote.TrackInfoRemoteDataSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val dataSourceModule = module {
    factoryOf(::AlbumInfoRemoteDataSourceImpl) { bind<AlbumInfoRemoteDataSource>() }
    factoryOf(::TrackInfoRemoteDataSourceImpl) { bind<TrackInfoRemoteDataSource>() }
}