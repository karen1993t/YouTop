package com.youtop.data.track_info.models


import com.google.gson.annotations.SerializedName
import com.youtop.core.mapper.Mappable
import com.youtop.data.BuildConfig
import com.youtop.domain.models.*

internal data class TrackInfoItemResponseModel(
    @SerializedName("albums")
    val albums: List<AlbumResponseModel>?,
    @SerializedName("artists")
    val artists: List<ArtistResponseModel>?,
    @SerializedName("contentWarning")
    val contentWarning: Boolean?,
    @SerializedName("coverImage")
    val coverImage: CoverImageResponseModel?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("downloaded")
    val downloaded: Boolean?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean?,
    @SerializedName("isHidden")
    val isHidden: Boolean?,
    @SerializedName("isTop")
    val isTop: Boolean?,
    @SerializedName("likeCount")
    val likeCount: Int?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("song")
    val song: SongResponseModel?,
    @SerializedName("subTitle")
    val subTitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
) : Mappable<TrackInfoItemDomainModel> {

    override fun map(): TrackInfoItemDomainModel = TrackInfoItemDomainModel(
        albums = albums?.map {
            TrackAlbumDomainModel(
                coverImage = CoverImageDomainModel(
                    dimensions = DimensionsDomainModel(
                        height = it.coverImage?.dimensions?.height ?: 0,
                        width = it.coverImage?.dimensions?.width ?: 0
                    ),
                    extension = it.coverImage?.extension.orEmpty(),
                    id = it.coverImage?.id.orEmpty(),
                    name = it.coverImage?.name.orEmpty(),
                    path = "${BuildConfig.BASE_URL_IMG}${it.coverImage?.path.orEmpty()}"
                ),
                id = it.id.orEmpty(),
                slug = it.slug.orEmpty(),
                isHidden = it.isHidden ?: true,
                subTitle = it.subTitle.orEmpty(),
                title = it.title.orEmpty(),
                type = it.type.orEmpty(),
                year = it.year ?: 0
            )
        } ?: emptyList(),
        artists = artists?.map {
            TrackArtistDomainModel(
                fullName = it.fullName.orEmpty(),
                id = it.id.orEmpty(),
                isHidden = it.isHidden ?: true,
                profileImage = TrackArtistDomainModel.ProfileImage(
                    extension = it.profileImage?.extension.orEmpty(),
                    id = it.profileImage?.id.orEmpty(),
                    name = it.profileImage?.name.orEmpty(),
                    path = "${BuildConfig.BASE_URL_IMG}${it.profileImage?.path.orEmpty()}"
                ),
                slug = it.slug.orEmpty()
            )
        } ?: emptyList(),
        contentWarning = contentWarning ?: false,
        coverImage = TrackCoverImageDomainModel(
            dimensions = TrackCoverImageDomainModel.Dimensions(
                height = coverImage?.dimensions?.height ?: 0,
                width = coverImage?.dimensions?.width ?: 0,
            ),
            extension = coverImage?.extension.orEmpty(),
            id = coverImage?.id.orEmpty(),
            name = coverImage?.name.orEmpty(),
            path = "${BuildConfig.BASE_URL_IMG}${coverImage?.path.orEmpty()}"
        ),
        createdAt = createdAt.orEmpty(),
        description = description.orEmpty(),
        downloaded = downloaded ?: false,
        id = id.orEmpty(),
        isFavorite = isFavorite ?: false,
        isHidden = isHidden ?: true,
        isTop = isTop ?: false,
        likeCount = likeCount ?: 0,
        slug = slug.orEmpty(),
        song = SongDomainModel(
            duration = song?.duration ?: 0.0,
            extension = song?.extension.orEmpty(),
            id = song?.id.orEmpty(),
            name = song?.name.orEmpty(),
            path = "${BuildConfig.BASE_URL_IMG}${song?.path.orEmpty()}"
        ),
        subTitle = subTitle.orEmpty(),
        title = title.orEmpty(),
        type = type.orEmpty()
    )

}

internal data class AlbumResponseModel(
    @SerializedName("coverImage")
    val coverImage: CoverImageResponseModel?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isHidden")
    val isHidden: Boolean?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("subTitle")
    val subTitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("year")
    val year: Int?
)

internal data class ArtistResponseModel(
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isHidden")
    val isHidden: Boolean?,
    @SerializedName("profileImage")
    val profileImage: ProfileImage?,
    @SerializedName("slug")
    val slug: String?
) {
    data class ProfileImage(
        @SerializedName("extension")
        val extension: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("path")
        val path: String?
    )
}

internal data class CoverImageResponseModel(
    @SerializedName("dimensions")
    val dimensions: Dimensions?,
    @SerializedName("extension")
    val extension: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("path")
    val path: String?
) {
    data class Dimensions(
        @SerializedName("height")
        val height: Int?,
        @SerializedName("width")
        val width: Int?
    )
}

internal data class SongResponseModel(
    @SerializedName("duration")
    val duration: Double?,
    @SerializedName("extension")
    val extension: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("path")
    val path: String?
)
