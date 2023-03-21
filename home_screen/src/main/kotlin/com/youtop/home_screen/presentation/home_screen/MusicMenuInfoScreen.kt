package com.youtop.home_screen.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youtop.core.extensions.clickableSingle
import com.youtop.home_screen.models.MusicMenuInfoModel

@Composable
internal fun MusicMenuInfoScreen(
    modifier: Modifier,
    data: List<MusicMenuInfoModel>,
    onItemClicked: ((MusicMenuInfoModel) -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(34.dp),
        contentPadding = PaddingValues(top = 5.dp, bottom = 47.dp)
    ) {
        items(items = data, key = { it.id }) { item ->
            MusicMenuItemScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickableSingle { onItemClicked?.invoke(item) },
                data = item
            )
        }
    }
}