package com.youtop.home_screen.mapper

import android.content.Context
import com.youtop.core.R
import com.youtop.core.mapper.MappableFrom
import com.youtop.domain.models.AlbumsInfoDomainModelItem
import com.youtop.home_screen.models.AlbumInfoItemUIModel
import kotlin.random.Random


class AlbumInfoDomainToUIMapper(private val context: Context) :
    MappableFrom<AlbumsInfoDomainModelItem, AlbumInfoItemUIModel> {

    override fun mapFrom(input: AlbumsInfoDomainModelItem): AlbumInfoItemUIModel =
        AlbumInfoItemUIModel(
            albumId = input.id,
            albumTitle = input.title,
            albumSubTitle = input.subTitle,
            id = Random.nextInt(),
            imgUrl = input.coverImage.path,
            albumTrackCountInfo = String.format(
                context.getString(R.string.track_count_name_formatter, input.tracksCount)
            )
        )
}