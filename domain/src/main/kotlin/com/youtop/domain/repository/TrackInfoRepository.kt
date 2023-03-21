package com.youtop.domain.repository

import com.youtop.core.models.Result
import com.youtop.domain.models.TrackInfoItemDomainModel
import kotlinx.coroutines.flow.Flow

interface TrackInfoRepository {
    suspend fun getTrackInfoData(albumId: String): TrackInfoItemDomainModel
    fun getAllSongsInfoData(limit: Int): Flow<Result<List<TrackInfoItemDomainModel>>>

}