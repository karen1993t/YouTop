package com.youtop.domain.usecase

import com.youtop.core.usecase.CoroutineUseCase
import com.youtop.domain.models.TrackInfoItemDomainModel
import com.youtop.domain.repository.TrackInfoRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetTrackDataUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val trackInfoRepository: TrackInfoRepository
) : CoroutineUseCase<String, TrackInfoItemDomainModel>(coroutineDispatcher) {

    override suspend fun execute(parameters: String): TrackInfoItemDomainModel =
        trackInfoRepository.getTrackInfoData(albumId = parameters)
}