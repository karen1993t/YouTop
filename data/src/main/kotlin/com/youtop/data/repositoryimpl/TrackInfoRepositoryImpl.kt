package com.youtop.data.repositoryimpl

import com.youtop.core.models.Result
import com.youtop.data.track_info.datasource.remote.TrackInfoRemoteDataSource
import com.youtop.domain.models.TrackInfoItemDomainModel
import com.youtop.domain.repository.TrackInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TrackInfoRepositoryImpl(
    private val trackInfoRemoteDataSource: TrackInfoRemoteDataSource
) : TrackInfoRepository {

    override suspend fun getTrackInfoData(albumId: String): TrackInfoItemDomainModel =

        trackInfoRemoteDataSource.getTrackData(albumId).map()

    override fun getAllSongsInfoData(limit: Int): Flow<Result<List<TrackInfoItemDomainModel>>> =
        flow {
            try {
                val responseData = trackInfoRemoteDataSource.getAllSongsInfo(limit)
                emit(Result.Success(responseData.map { it.map() }))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}