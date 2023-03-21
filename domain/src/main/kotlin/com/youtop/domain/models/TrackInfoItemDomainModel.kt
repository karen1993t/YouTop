package com.youtop.domain.models


data class TrackInfoItemDomainModel(
    val albums: List<TrackAlbumDomainModel>,
    val artists: List<TrackArtistDomainModel>,
    val contentWarning: Boolean,
    val coverImage: TrackCoverImageDomainModel,
    val createdAt: String,
    val description: String,
    val downloaded: Boolean,
    val id: String,
    val isFavorite: Boolean,
    val isHidden: Boolean,
    val isTop: Boolean,
    val likeCount: Int,
    val slug: String,
    val song: SongDomainModel,
    val subTitle: String,
    val title: String,
    val type: String
)

data class TrackAlbumDomainModel(
    val coverImage: CoverImageDomainModel,
    val id: String,
    val isHidden: Boolean,
    val slug: String,
    val subTitle: String,
    val title: String,
    val type: String,
    val year: Int
)

data class TrackArtistDomainModel(
    val fullName: String,
    val id: String,
    val isHidden: Boolean,
    val profileImage: ProfileImage,
    val slug: String
) {
    data class ProfileImage(
        val extension: String,
        val id: String,
        val name: String,
        val path: String
    )
}

data class TrackCoverImageDomainModel(
    val dimensions: Dimensions,
    val extension: String,
    val id: String,
    val name: String,
    val path: String
) {
    data class Dimensions(
        val height: Int,
        val width: Int
    )
}

data class SongDomainModel(
    val duration: Double,
    val extension: String,
    val id: String,
    val name: String,
    val path: String
)
