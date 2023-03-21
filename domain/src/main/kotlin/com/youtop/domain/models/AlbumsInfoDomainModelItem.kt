package com.youtop.domain.models


 data class AlbumsInfoDomainModelItem(
    val artists: List<ArtistDomainModel>,
    val contentWarning: Boolean,
    val coverImage: CoverImageDomainModel,
    val id: String,
    val isFavorite: Boolean,
    val likeCount: Int,
    val slug: String,
    val subTitle: String,
    val title: String,
    val tracksCount: Int,
    val type: String
)


 data class ArtistDomainModel(
    val fullName: String,
    val id: String,
    val isHidden: Boolean,
    val profileImage: ProfileImageDomainModel,
    val slug: String
)

 data class CoverImageDomainModel(
    val dimensions: DimensionsDomainModel,
    val extension: String,
    val id: String,
    val name: String,
    val path: String
)

 data class DimensionsDomainModel(
    val height: Int,
    val width: Int
)

 data class ProfileImageDomainModel(
    val extension: String,
    val id: String,
    val name: String,
    val path: String
)