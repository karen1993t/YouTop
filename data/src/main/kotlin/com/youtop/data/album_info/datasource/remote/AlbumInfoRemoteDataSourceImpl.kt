package com.youtop.data.album_info.datasource.remote

import com.youtop.data.album_info.models.AlbumsInfoResponseModelItem
import com.youtop.data.api.YouTopAppRestServiceApi

internal class AlbumInfoRemoteDataSourceImpl(
    private val youTopAppRestServiceApi: YouTopAppRestServiceApi
) : AlbumInfoRemoteDataSource {

    override suspend fun getAlbumInfoData(
        limit: Int,
        offset: Int
    ): List<AlbumsInfoResponseModelItem> = youTopAppRestServiceApi.getMusicAlbums(limit, offset)
}