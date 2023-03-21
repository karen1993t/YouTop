package com.youtop.data.album_info.models


import com.google.gson.annotations.SerializedName
import com.youtop.core.mapper.Mappable
import com.youtop.data.BuildConfig
import com.youtop.domain.models.*

internal data class AlbumsInfoResponseModelItem(
    @SerializedName("artists")
    val artists: List<ArtistResponseModel>?,
    @SerializedName("contentWarning")
    val contentWarning: Boolean?,
    @SerializedName("coverImage")
    val coverImage: CoverImageResponseModel?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean?,
    @SerializedName("likeCount")
    val likeCount: Int?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("subTitle")
    val subTitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("tracksCount")
    val tracksCount: Int?,
    @SerializedName("type")
    val type: String?
) : Mappable<AlbumsInfoDomainModelItem> {
    override fun map(): AlbumsInfoDomainModelItem = AlbumsInfoDomainModelItem(
        id = id.orEmpty(),
        artists = artists?.map {
            ArtistDomainModel(
                fullName = it.fullName.orEmpty(),
                id = it.id.orEmpty(),
                isHidden = it.isHidden ?: true,
                profileImage = ProfileImageDomainModel(
                    extension = it.profileImage?.extension.orEmpty(),
                    id = it.profileImage?.id.orEmpty(),
                    name = it.profileImage?.name.orEmpty(),
                    path = "${BuildConfig.BASE_URL_IMG}${it.profileImage?.path.orEmpty()}"
                ),
                slug = it.slug.orEmpty()
            )
        } ?: emptyList(),
        contentWarning = contentWarning ?: false,
        coverImage = CoverImageDomainModel(
            dimensions = DimensionsDomainModel(
                height = coverImage?.dimensions?.height ?: 0,
                width = coverImage?.dimensions?.width ?: 0
            ),
            extension = coverImage?.extension.orEmpty(),
            id = coverImage?.id.orEmpty(),
            name = coverImage?.name.orEmpty(),
            path = "${BuildConfig.BASE_URL_IMG}${coverImage?.path.orEmpty()}"
        ),
        isFavorite = isFavorite ?: false,
        likeCount = likeCount ?: 0,
        slug = slug.orEmpty(),
        subTitle = subTitle.orEmpty(),
        title = title.orEmpty(),
        tracksCount = tracksCount ?: 0,
        type = type.orEmpty()
    )
}


internal data class ArtistResponseModel(
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isHidden")
    val isHidden: Boolean?,
    @SerializedName("profileImage")
    val profileImage: ProfileImageResponseModel?,
    @SerializedName("slug")
    val slug: String?
)

internal data class CoverImageResponseModel(
    @SerializedName("dimensions")
    val dimensions: DimensionsResponseModel?,
    @SerializedName("extension")
    val extension: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("path")
    val path: String?
)

internal data class DimensionsResponseModel(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("width")
    val width: Int?
)

internal data class ProfileImageResponseModel(
    @SerializedName("extension")
    val extension: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("path")
    val path: String?
)