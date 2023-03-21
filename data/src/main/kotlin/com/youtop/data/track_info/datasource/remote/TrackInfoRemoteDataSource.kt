package com.youtop.data.track_info.datasource.remote

import com.youtop.data.track_info.models.TrackInfoItemResponseModel

internal interface TrackInfoRemoteDataSource {
    suspend fun getTrackData(albumId: String): TrackInfoItemResponseModel
    suspend fun getAllSongsInfo(limit:Int):List<TrackInfoItemResponseModel>
}