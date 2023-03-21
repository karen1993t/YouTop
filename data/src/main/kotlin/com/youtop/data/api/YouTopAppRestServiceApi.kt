package com.youtop.data.api

import com.youtop.data.album_info.models.AlbumsInfoResponseModelItem
import com.youtop.data.api.ApiPath.GET_All_TRACK
import com.youtop.data.api.ApiPath.GET_MUSIC_ALBUMS
import com.youtop.data.api.ApiPath.GET_TRACK
import com.youtop.data.track_info.models.TrackInfoItemResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface YouTopAppRestServiceApi {
    @GET(GET_MUSIC_ALBUMS)
    suspend fun getMusicAlbums(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): List<AlbumsInfoResponseModelItem>

    @GET(GET_TRACK)
    suspend fun getTrackById(
        @Path("id") id: String
    ): TrackInfoItemResponseModel

    @GET(GET_All_TRACK)
    suspend fun getAllSongs(
        @Query("limit") limit: Int,
    ): List<TrackInfoItemResponseModel>
}