package com.youtop.home_screen.presentation.home_screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.youtop.core.R
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppTheme
import com.youtop.home_screen.models.AlbumInfoItemUIModel

@Composable
fun AlbumItemInfoScreen(
    modifier: Modifier,
    data: AlbumInfoItemUIModel,
    isPreviewMode: Boolean = false
) {
    Column(
        modifier = modifier
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start

    ) {
        if (isPreviewMode) {
            Image(
                modifier = Modifier
                    .size(width = 110.dp, height = 110.dp),
                painter = painterResource(id = R.mipmap.ic_album_preview_mode),
                contentDescription = null
            )
        } else {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(width = 110.dp, height = 110.dp)
                    .clip(YouTopAppTheme.roundedCornerShape.shapeSmall),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                model = data.imgUrl,
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

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = data.albumTitle,
            style = YouTopAppTheme.typography.labelLarge,
            color = YouTopAppTheme.colors.primaryTitle,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = data.albumTrackCountInfo,
            style = YouTopAppTheme.typography.labelMedium,
            color = YouTopAppTheme.colors.secondaryLabel,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AlbumInfoItemLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        AlbumItemInfoScreen(
            modifier = Modifier
                .wrapContentHeight()
                .requiredWidth(110.dp)
                .background(YouTopAppTheme.colors.primaryBackground),
            data = AlbumInfoItemUIModel.initial(),
            isPreviewMode = true
        )
    }
}