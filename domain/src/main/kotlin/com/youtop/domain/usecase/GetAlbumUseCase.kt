package com.youtop.domain.usecase

import com.youtop.core.models.Result
import com.youtop.core.usecase.FlowUseCase
import com.youtop.domain.models.AlbumsInfoDomainModelItem
import com.youtop.domain.models.GetAlbumRequestModel
import com.youtop.domain.repository.AlbumInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetAlbumUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val albumInfoRepository: AlbumInfoRepository
) : FlowUseCase<GetAlbumRequestModel, List<AlbumsInfoDomainModelItem>>(coroutineDispatcher) {

    override fun execute(parameters: GetAlbumRequestModel): Flow<Result<List<AlbumsInfoDomainModelItem>>> =

        albumInfoRepository.getAlbumInfoData(limit = parameters.limit, offset = parameters.offset)
}