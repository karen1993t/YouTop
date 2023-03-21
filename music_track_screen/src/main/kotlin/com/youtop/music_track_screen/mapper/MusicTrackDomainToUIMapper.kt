package com.youtop.music_track_screen.mapper

import android.content.Context
import com.youtop.core.R
import com.youtop.core.mapper.MappableFrom
import com.youtop.domain.models.TrackInfoItemDomainModel
import com.youtop.music_track_screen.models.MusicTrackUIModel
import kotlin.random.Random

internal class MusicTrackDomainToUIMapper(private val context: Context) :
    MappableFrom<TrackInfoItemDomainModel, MusicTrackUIModel> {

    override fun mapFrom(input: TrackInfoItemDomainModel): MusicTrackUIModel =
        MusicTrackUIModel(
            id = Random.nextInt(),
            trackId = input.id,
            artistFullName = input.artists.firstOrNull()?.fullName.orEmpty(),
            subTitle = input.subTitle,
            profileImageUrl = input.artists.firstOrNull()?.profileImage?.path.orEmpty(),
            songId = input.song.id,
            songUrl = input.song.path,
            songName = input.song.name,
            songDuration = input.song.duration,
            trackLikeCountInfo = String.format(
                context.getString(R.string.track_like_info_name_formatter, input.likeCount)
            )
        )
}