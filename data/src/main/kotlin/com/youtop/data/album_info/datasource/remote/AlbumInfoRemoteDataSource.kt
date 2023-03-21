package com.youtop.data.album_info.datasource.remote

import com.youtop.data.album_info.models.AlbumsInfoResponseModelItem


internal interface AlbumInfoRemoteDataSource {
    suspend fun getAlbumInfoData(limit: Int, offset: Int): List<AlbumsInfoResponseModelItem>
}