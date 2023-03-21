package com.youtop.data.repositoryimpl

import com.youtop.core.models.Result
import com.youtop.data.album_info.datasource.remote.AlbumInfoRemoteDataSource
import com.youtop.domain.models.AlbumsInfoDomainModelItem
import com.youtop.domain.repository.AlbumInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class AlbumInfoRepositoryImpl(
    private val albumInfoRemoteDataSource: AlbumInfoRemoteDataSource,
) : AlbumInfoRepository {

    override fun getAlbumInfoData(
        limit: Int,
        offset: Int
    ): Flow<Result<List<AlbumsInfoDomainModelItem>>> = flow {
        try {
            val responseData = albumInfoRemoteDataSource.getAlbumInfoData(limit, offset)
            emit(Result.Success(responseData.map { it.map() }))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}