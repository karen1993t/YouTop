package com.youtop.music_track_screen.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.youtop.core.R
import com.youtop.core.extensions.clickableSingle
import com.youtop.core.theme.YouTopAppExerciseTheme

@Composable
fun MediaPlayerButtonsContainerScreen(
    modifier: Modifier,
    shareButtonClicked: (() -> Unit)? = null,
    previewSongBtnClicked: (() -> Unit)? = null,
    nextSongBtnClicked: (() -> Unit)? = null,
    playSongBtnClicked: (() -> Unit)? = null,
    likeSongBtnClicked: (() -> Unit)? = null,

    ) {

    Row(
        modifier = modifier
            .padding(start = 66.dp, end = (63.89).dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .requiredSize(width = (17.28).dp, height = (14.52).dp)
                .clickableSingle { shareButtonClicked?.invoke() },
            contentDescription = null,
            painter = painterResource(id = R.drawable.ic_share)
        )
        Spacer(modifier = Modifier.width((42.72).dp))

        Image(
            modifier = Modifier
                .requiredSize(width = (12).dp, height = (14).dp)
                .clickableSingle { previewSongBtnClicked?.invoke() },
            contentDescription = null,
            painter = painterResource(id = R.drawable.ic_play_back)
        )

        Spacer(modifier = Modifier.width(28.dp))

        Box(
            modifier = Modifier
                .requiredSize(42.dp)
                .clickableSingle { playSongBtnClicked?.invoke() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clickableSingle { shareButtonClicked?.invoke() },
                contentDescription = null,
                painter = painterResource(id = R.drawable.background_track_play)
            )
            Image(
                modifier = Modifier
                    .requiredSize(24.dp),
                contentDescription = null,
                painter = painterResource(id = R.drawable.ic_play)
            )
        }

        Spacer(modifier = Modifier.width(32.dp))

        Image(
            modifier = Modifier
                .requiredSize(width = (12).dp, height = (14).dp)
                .clickableSingle { nextSongBtnClicked?.invoke() },
            contentDescription = null,
            painter = painterResource(id = R.drawable.ic_play_next)
        )

        Spacer(modifier = Modifier.width(42.dp))

        Image(
            modifier = Modifier
                .requiredSize(width = (18.11).dp, height = (15.55).dp)
                .clickableSingle { likeSongBtnClicked?.invoke() },
            contentDescription = null,
            painter = painterResource(id = R.drawable.ic_favorite_white)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MediaPlayerButtonsContainerScreenLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        MediaPlayerButtonsContainerScreen(
            modifier = Modifier
                .wrapContentSize()
        )
    }
}