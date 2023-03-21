package com.youtop.domain.repository

import com.youtop.core.models.Result
import com.youtop.domain.models.AlbumsInfoDomainModelItem
import kotlinx.coroutines.flow.Flow

interface AlbumInfoRepository {
    fun getAlbumInfoData(limit: Int, offset: Int): Flow<Result<List<AlbumsInfoDomainModelItem>>>
}