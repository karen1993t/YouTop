package com.youtop.music_track_screen.mapper

import com.youtop.core.mapper.MappableFrom
import com.youtop.domain.models.TrackInfoItemDomainModel
import com.youtop.music_track_screen.models.SongItemUIModel
import kotlin.random.Random

internal class MusicTrackDomainToSongItemUIMapper :
    MappableFrom<TrackInfoItemDomainModel, SongItemUIModel> {

    override fun mapFrom(input: TrackInfoItemDomainModel): SongItemUIModel =
        SongItemUIModel(
            id = Random.nextInt(),
            songId = input.song.id,
            artistFullName = input.artists.firstOrNull()?.fullName.orEmpty(),
            albumsTitle = input.albums.firstOrNull()?.title.orEmpty(),
            songArtistImgUrl = input.albums.firstOrNull()?.coverImage?.path.orEmpty()
        )
}