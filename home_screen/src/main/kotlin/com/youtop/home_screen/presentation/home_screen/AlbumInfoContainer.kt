package com.youtop.home_screen.presentation.home_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.youtop.core.extensions.clickableSingle
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppTheme
import com.youtop.home_screen.models.AlbumInfoItemUIModel

@Composable
internal fun AlbumInfoContainer(
    modifier: Modifier,
    lazyScrollState: LazyListState,
    data: List<AlbumInfoItemUIModel>,
    isPreviewMode: Boolean = false,
    onItemClicked: ((AlbumInfoItemUIModel) -> Unit)? = null
) {
    LazyRow(
        modifier = modifier
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 5.dp
        ),
        state = lazyScrollState
    ) {
        items(items = data, key = { it.id }) { item ->
            AlbumItemInfoScreen(
                modifier = Modifier
                    .requiredWidth(110.dp)
                    .wrapContentHeight()
                    .clickableSingle { onItemClicked?.invoke(item) },
                data = item,
                isPreviewMode = isPreviewMode
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AlbumInfoContainerLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        AlbumInfoContainer(
            modifier = Modifier
                .wrapContentHeight()
                .background(YouTopAppTheme.colors.primaryBackground),
            lazyScrollState = rememberLazyListState(),
            data = listOf(
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial(),
                AlbumInfoItemUIModel.initial()
            ),
            isPreviewMode = true
        )
    }
}