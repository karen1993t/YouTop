package com.youtop.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.youtop.core.R
import com.youtop.core.extensions.clickableSingle
import com.youtop.core.theme.YouTopAppExerciseTheme
import com.youtop.core.theme.YouTopAppTheme

@Composable
fun AppTopBar(
    title: String,
    actions: (() -> Unit)? = null
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(64.dp)
            .background(YouTopAppTheme.colors.primaryBackground)
            .shadow(
                elevation = 1.dp,
                shape = RectangleShape,
                spotColor = YouTopAppTheme.colors.shadow
            )

    ) {
        val (titleTopAppBar, notifyIcon) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(titleTopAppBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 11.dp)
                    top.linkTo(parent.top, 23.dp)

                }
                .wrapContentSize(),
            text = title,
            style = YouTopAppTheme.typography.titleLarge
        )
        Image(
            modifier = Modifier
                .constrainAs(notifyIcon) {
                    end.linkTo(parent.end, 13.dp)
                    bottom.linkTo(parent.bottom, 11.dp)
                    top.linkTo(parent.top, 29.dp)
                }
                .size(width = 24.dp, height = 24.dp)
                .clickableSingle { actions?.invoke() },
            painter = painterResource(id = R.drawable.ic_notific),
            contentDescription = null,
            contentScale = ContentScale.Inside
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun TopAppBarLightPreviewMode() {
    YouTopAppExerciseTheme(darkTheme = false) {
        AppTopBar(title = "YouTop")
    }
}