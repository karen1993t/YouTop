package com.youtop.home_screen.presentation.home_screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.youtop.core.R
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppTheme
import com.youtop.home_screen.models.AlbumInfoItemUIModel

@Composable
internal fun MusicAlbumInfoContainer(
    modifier: Modifier,
    albumInfoData: List<AlbumInfoItemUIModel>,
    isPreviewMode: Boolean = false,
    seeAllBtnClicked: (() -> Unit)? = null,
    onItemClicked: ((AlbumInfoItemUIModel) -> Unit)? = null
) {
    val lazyScrollState = rememberLazyListState()

    ConstraintLayout(modifier = modifier) {

        val (title, seeAllButton, albumInfoList) = createRefs()

        Text(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(title) {
                    start.linkTo(parent.start, 12.dp)
                    top.linkTo(parent.top, 22.dp)
                },
            text = stringResource(id = R.string.home_screen_title),
            style = YouTopAppTheme.typography.titleMediumRegular,
            maxLines = 1,
            textAlign = TextAlign.Start,
            color = YouTopAppTheme.colors.primaryTitle
        )
        Button(
            modifier = Modifier
                .requiredWidth(43.dp)
                .requiredHeight(16.dp)
                .alpha(0.9f)
                .constrainAs(seeAllButton) {
                    end.linkTo(parent.end, 12.dp)
                    top.linkTo(parent.top, 25.dp)
                },
            border = BorderStroke(
                width = 1.dp,
                color = YouTopAppTheme.colors.buttonPrimaryBorderStrokeColor
            ),

            contentPadding = PaddingValues(
                start = 6.dp,
                end = 6.dp,
                top = 3.dp,
                bottom = 3.dp
            ),
            onClick = { seeAllBtnClicked?.invoke() },
            colors = ButtonDefaults.buttonColors(
                containerColor = YouTopAppTheme.colors.buttonPrimaryBackGround
            ),
            shape = YouTopAppTheme.roundedCornerShape.shapeSmall
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.home_screen_see_all_btn_text),
                style = YouTopAppTheme.typography.labelSmall,
                color = YouTopAppTheme.colors.buttonPrimaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        AlbumInfoContainer(
            modifier = Modifier
                .padding(start = 12.dp)
                .constrainAs(albumInfoList) {
                    end.linkTo(parent.end, 13.dp)
                    top.linkTo(title.bottom, 13.dp)
                },
            lazyScrollState = lazyScrollState,
            data = albumInfoData,
            isPreviewMode = isPreviewMode
        ) {
            onItemClicked?.invoke(it)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MusicAlbumInfoLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        MusicAlbumInfoContainer(
            modifier = Modifier
                .background(color = YouTopAppTheme.colors.primaryBackground),
            albumInfoData = listOf(
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial()
            ),
            isPreviewMode = true
        )
    }
}


