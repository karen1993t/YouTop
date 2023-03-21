package com.youtop.domain.usecase

import com.youtop.core.models.Result
import com.youtop.core.usecase.FlowUseCase
import com.youtop.domain.models.TrackInfoItemDomainModel
import com.youtop.domain.repository.TrackInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetAllSongsUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val trackInfoRepository: TrackInfoRepository
) : FlowUseCase<Int, List<TrackInfoItemDomainModel>>(coroutineDispatcher) {

    override fun execute(parameters: Int): Flow<Result<List<TrackInfoItemDomainModel>>> =
        trackInfoRepository.getAllSongsInfoData(limit = parameters)
}