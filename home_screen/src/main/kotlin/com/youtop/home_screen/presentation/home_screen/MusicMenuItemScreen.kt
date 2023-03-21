package com.youtop.home_screen.presentation.home_screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.youtop.core.extensions.clickableSingle
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppTheme
import com.youtop.home_screen.models.MusicMenuInfoModel

@Composable
fun MusicMenuItemScreen(modifier: Modifier, data: MusicMenuInfoModel) {
    Row(
        modifier = modifier
            .requiredHeight(30.dp)
            .background(YouTopAppTheme.colors.primaryBackground)
            .clickableSingle { }
            .padding(start = 15.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(width = 24.dp, height = 24.dp),
            painter = painterResource(id = data.resIcon),
            contentDescription = stringResource(id = data.resTitle)
        )
        Spacer(modifier = Modifier.width(13.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = stringResource(id = data.resTitle),
            style = YouTopAppTheme.typography.musicMenuItem,
            color = YouTopAppTheme.colors.primaryTitle
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MusicMenuItemLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        MusicMenuItemScreen(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp),
            data = MusicMenuInfoModel.initial()
        )
    }
}