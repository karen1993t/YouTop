package com.youtop.music_track_screen.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.youtop.core.R
import com.youtop.core.extensions.clickableSingle
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppTheme
import com.youtop.music_track_screen.models.SongItemUIModel

@Composable
internal fun SongInfoItemScreen(
    modifier: Modifier,
    data: SongItemUIModel,
    onItemClicked: ((SongItemUIModel) -> Unit)? = null,
    onItemMenuClicked: ((SongItemUIModel) -> Unit)? = null,
    isPreviewMode: Boolean = false
) {
    ConstraintLayout(
        modifier = modifier
            .wrapContentHeight()
            .clickableSingle { onItemClicked?.invoke(data) }
            .padding(vertical = 1.dp, horizontal = 1.dp),

        ) {
        val (songImgContainer, songTitle, songSubtitle, bottomLine, menuIconContainer) = createRefs()

        if (isPreviewMode) {
            Image(
                modifier = Modifier
                    .constrainAs(songImgContainer) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.value(56.dp)
                        width = Dimension.value(56.dp)
                    }
                    .clip(YouTopAppTheme.roundedCornerShape.shapeSmall),
                painter = painterResource(id = R.mipmap.ic_album_preview_mode),
                contentDescription = null
            )
        } else {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .constrainAs(songImgContainer) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.value(56.dp)
                        width = Dimension.value(56.dp)
                    }
                    .clip(YouTopAppTheme.roundedCornerShape.shapeSmall),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                model = data.songArtistImgUrl,
                content = {
                    when (painter.state) {
                        is AsyncImagePainter.State.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(
                                        Alignment.Center
                                    ),
                                color = YouTopAppTheme.colors.shadow
                            )
                        }
                        is AsyncImagePainter.State.Error -> {
                            SubcomposeAsyncImageContent(painter = painterResource(id = R.mipmap.error_album_img))
                        }
                        else -> {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            )
        }

        Text(
            modifier = Modifier
                .constrainAs(songTitle) {
                    start.linkTo(songImgContainer.end, 18.dp)
                    top.linkTo(songImgContainer.top)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                },
            text = data.artistFullName,
            style = YouTopAppTheme.typography.titleMediumRegular,
            color = YouTopAppTheme.colors.primaryTitle,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .constrainAs(songSubtitle) {
                    start.linkTo(songTitle.start)
                    top.linkTo(songTitle.bottom)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                },
            text = data.albumsTitle,
            style = YouTopAppTheme.typography.labelLarge,
            color = YouTopAppTheme.colors.secondaryLabel,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(
            modifier = Modifier
                .constrainAs(bottomLine) {
                    start.linkTo(songTitle.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(1.dp)
                }
                .background(YouTopAppTheme.colors.lineColor)
        )

        Image(
            modifier = Modifier
                .constrainAs(menuIconContainer) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    height = Dimension.value(24.dp)
                    width = Dimension.value(24.dp)
                }
                .clickableSingle { onItemMenuClicked?.invoke(data) },
            painter = painterResource(id = R.drawable.ic_menu_dark),
            contentScale = ContentScale.Inside,
            contentDescription = null
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AlbumInfoItemLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        SongInfoItemScreen(
            modifier = Modifier
                .wrapContentHeight()
                .requiredWidth(276.dp)
                .background(YouTopAppTheme.colors.primaryBackground),
            data = SongItemUIModel.initial(),
            isPreviewMode = true
        )
    }
}