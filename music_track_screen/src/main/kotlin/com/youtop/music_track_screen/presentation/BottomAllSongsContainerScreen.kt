package com.youtop.music_track_screen.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.youtop.core.R
import com.youtop.core.models.ViewState
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppTheme
import com.youtop.music_track_screen.viewmodel.MusicTrackViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun BottomAllSongsContainerScreen(
    modifier: Modifier,
    showMessage: ((String) -> Unit)? = null
) {
    val lazyScrollState = rememberLazyListState()
    val musicTrackViewModel: MusicTrackViewModel = koinViewModel()
    val allSongViewState by musicTrackViewModel.allSongsViewState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = musicTrackViewModel) {
        musicTrackViewModel.loadAllSongsData()
    }

    Column(
        modifier = modifier
            .clip(YouTopAppTheme.roundedCornerShape.shapeLarge)
            .background(YouTopAppTheme.colors.primaryBackground)
            .padding(top = 45.dp, start = 16.dp, end = 8.dp, bottom = 59.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start

    ) {
        Text(
            text = stringResource(id = R.string.all_songs_title),
            style = YouTopAppTheme.typography.titleMedium,
            color = YouTopAppTheme.colors.primaryTitle
        )
        Spacer(modifier = Modifier.height(24.dp))

        when (val songsViewState = allSongViewState) {
            is ViewState.Success -> {
                AllSongInfoContainerScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    lazyScrollState = lazyScrollState,
                    data = songsViewState.data,
                    onItemClicked = {
                        showMessage?.invoke("artist item ${it.albumsTitle} clicked")
                    }
                )
            }
            is ViewState.Loading -> {}
            is ViewState.Error -> {
                showMessage?.invoke(songsViewState.toString())
            }
            is ViewState.Empty -> {
                showMessage?.invoke(songsViewState.toString())
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BottomAllSongsContainerScreenLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        BottomAllSongsContainerScreen(
            modifier = Modifier
                .fillMaxSize()
                .clip(YouTopAppTheme.roundedCornerShape.shapeLarge)
                .background(YouTopAppTheme.colors.primaryBackground)
                .padding(top = 45.dp, start = 16.dp, end = 8.dp, bottom = 59.dp),
        )
    }
}