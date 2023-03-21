package com.youtop.music_track_screen.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.youtop.core.R
import com.youtop.core.extensions.clickableSingle
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppTheme

@Composable
internal fun TopAppBarContainerScreen(
    modifier: Modifier,
    title: String,
    onBackButtonClicked: (() -> Unit)? = null,
    onMenuIconClicked: (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(width = 24.dp, height = 24.dp)
                .clickableSingle { onBackButtonClicked?.invoke() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            contentScale = ContentScale.Inside
        )

        Text(
            text = title,
            style = YouTopAppTheme.typography.titleMediumBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = YouTopAppTheme.colors.thirdTitle
        )
        Image(
            modifier = Modifier
                .size(width = 24.dp, height = 24.dp)
                .clickableSingle { onMenuIconClicked?.invoke() },
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = null,
            contentScale = ContentScale.Inside
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun TopAppBarContainerLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        TopAppBarContainerScreen(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 4.dp),
            title = "Flames"
        )
    }
}