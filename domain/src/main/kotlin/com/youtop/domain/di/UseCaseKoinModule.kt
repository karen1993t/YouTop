package com.youtop.domain.di

import com.youtop.core.di.DispatchersName
import com.youtop.domain.usecase.GetAlbumUseCase
import com.youtop.domain.usecase.GetAllSongsUseCase
import com.youtop.domain.usecase.GetTrackDataUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val useCaseKoinModule = module {
    factory {
        GetAlbumUseCase(
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO)),
            albumInfoRepository = get()
        )
    }
    factory {
        GetTrackDataUseCase(
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO)),
            trackInfoRepository = get()
        )
    }
    factory {
        GetAllSongsUseCase(
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO)),
            trackInfoRepository = get()
        )
    }
}