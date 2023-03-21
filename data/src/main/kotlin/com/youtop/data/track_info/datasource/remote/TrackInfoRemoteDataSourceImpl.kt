package com.youtop.data.track_info.datasource.remote

import com.youtop.data.api.YouTopAppRestServiceApi
import com.youtop.data.track_info.models.TrackInfoItemResponseModel

internal class TrackInfoRemoteDataSourceImpl(
    private val youTopAppRestServiceApi: YouTopAppRestServiceApi
) : TrackInfoRemoteDataSource {

    override suspend fun getTrackData(albumId: String): TrackInfoItemResponseModel =
        youTopAppRestServiceApi.getTrackById(albumId)

    override suspend fun getAllSongsInfo(limit: Int): List<TrackInfoItemResponseModel> =
        youTopAppRestServiceApi.getAllSongs(limit = limit)
}