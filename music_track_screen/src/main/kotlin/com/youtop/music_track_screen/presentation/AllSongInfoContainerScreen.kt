package com.youtop.music_track_screen.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppTheme
import com.youtop.music_track_screen.models.SongItemUIModel

@Composable
internal fun AllSongInfoContainerScreen(
    modifier: Modifier,
    lazyScrollState: LazyListState,
    data: List<SongItemUIModel>,
    isPreviewMode: Boolean = false,
    onItemClicked: ((SongItemUIModel) -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(1f)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(17.dp),
        contentPadding = PaddingValues(bottom = 2.dp, top = 2.dp),
        state = lazyScrollState
    ) {
        items(items = data, key = { it.id }) { item ->
            SongInfoItemScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                data = item,
                isPreviewMode = isPreviewMode,
                onItemClicked = { onItemClicked?.invoke(item) }
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AlbumInfoContainerLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        AllSongInfoContainerScreen(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(1f)
                .background(YouTopAppTheme.colors.primaryBackground),
            lazyScrollState = rememberLazyListState(),
            data = listOf(
                SongItemUIModel.initial(),
                SongItemUIModel.initial(),
                SongItemUIModel.initial(),
                SongItemUIModel.initial(),
                SongItemUIModel.initial()
            ),
            isPreviewMode = true
        )
    }
}