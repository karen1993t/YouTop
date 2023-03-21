package com.youtop.music_track_screen.presentation

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.youtop.core.R
import com.youtop.core.models.ViewState
import com.youtop.core.theme.YouTopAppTheme
import com.youtop.music_track_screen.models.MusicTrackScreenSideEffect
import com.youtop.music_track_screen.models.MusicTrackUIModel
import com.youtop.music_track_screen.viewmodel.MusicTrackViewModel
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MusicTrackScreen(
    modifier: Modifier,
    albumId: String,
    navController: NavController,
    isPreviewMode: Boolean = false,
) {

    val musicTrackViewModel: MusicTrackViewModel = koinViewModel()
    val musicTrackScreenViewState by musicTrackViewModel.container.stateFlow.collectAsState()
    val activity = LocalContext.current as Activity
    val systemUiController = rememberSystemUiController()
    val statusBarColor: Color = YouTopAppTheme.colors.secondaryStatusBarColor
    val snackBarHostState = remember { mutableStateOf(SnackbarHostState()) }
    var isErrorMessageShow by remember {
        mutableStateOf(false)
    }

    var data by remember {
        mutableStateOf<MusicTrackUIModel?>(null)
    }

    LaunchedEffect(key1 = musicTrackViewModel) {
        WindowCompat.setDecorFitsSystemWindows(activity.window, true)
        systemUiController.setStatusBarColor(statusBarColor)
        musicTrackViewModel.loadData()
    }
    musicTrackViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is MusicTrackScreenSideEffect.NavigateToBackStack -> {
                navController.navigateUp()
            }
            is MusicTrackScreenSideEffect.ShowMessage -> {
                isErrorMessageShow = sideEffect.isErrorMessage
                snackBarHostState.value.showSnackbar(
                    message = sideEffect.message,
                    duration = SnackbarDuration.Long
                )
            }
        }
    }

    ConstraintLayout(modifier = modifier) {

        val (backGroundSongImg,
            background, topAppBarContainer, songImgContainer,
            mediaPlayerButtonsContainer, likeInfoIcon, marginChain,
            likeInfo, bottomSongInfoContainer, snackBar, progressIndicator) = createRefs()
        createHorizontalChain(likeInfoIcon, marginChain, likeInfo, chainStyle = ChainStyle.Packed)

        if (isPreviewMode) {
            Image(
                modifier = Modifier
                    .constrainAs(backGroundSongImg) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .requiredHeight(372.dp),
                contentDescription = null,
                painter = painterResource(id = R.mipmap.ic_album_preview_mode)
            )
        } else {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .constrainAs(backGroundSongImg) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                    }
                    .requiredHeight(372.dp)
                    .alpha(0.34f),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                model = data?.profileImageUrl,
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
                        else -> {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .background(YouTopAppTheme.colors.secondaryBackground)
        )

        TopAppBarContainerScreen(
            modifier = Modifier
                .constrainAs(topAppBarContainer) {
                    start.linkTo(parent.start, 11.dp)
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(parent.top, 31.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
                .padding(vertical = 4.dp),
            title = data?.artistFullName.orEmpty(),
            onBackButtonClicked = { musicTrackViewModel.onBackButtonClicked() },
            onMenuIconClicked = { musicTrackViewModel.showMessage(message = "menu icon clicked") }
        )

        if (isPreviewMode) {
            Image(
                modifier = Modifier
                    .constrainAs(songImgContainer) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topAppBarContainer.bottom, (25.5).dp)
                        width = Dimension.value(126.dp)
                        height = Dimension.value(126.dp)
                    },
                contentDescription = null,
                painter = painterResource(id = R.mipmap.ic_album_preview_mode)
            )
        } else {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .constrainAs(songImgContainer) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topAppBarContainer.bottom, (25.5).dp)
                        width = Dimension.value(126.dp)
                        height = Dimension.value(126.dp)
                    }
                    .clip(YouTopAppTheme.roundedCornerShape.shapeMedium),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                model = data?.profileImageUrl,
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
                        else -> {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            )
        }

        MediaPlayerButtonsContainerScreen(
            modifier = Modifier
                .constrainAs(mediaPlayerButtonsContainer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(songImgContainer.bottom, 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        )

        Image(
            modifier = Modifier
                .constrainAs(likeInfoIcon) {
                    start.linkTo(parent.start)
                    end.linkTo(marginChain.start)
                    top.linkTo(mediaPlayerButtonsContainer.bottom, 26.dp)
                    width = Dimension.value((9.53).dp)
                    height = Dimension.value(9.dp)
                },
            contentDescription = null,
            painter = painterResource(id = R.drawable.ic_favorite_white)
        )
        Spacer(
            modifier = Modifier
                .constrainAs(marginChain) {
                    start.linkTo(likeInfoIcon.end)
                    end.linkTo(likeInfo.start)
                    width = Dimension.value(8.dp)
                })

        Text(
            modifier = Modifier
                .constrainAs(likeInfo) {
                    linkTo(
                        start = marginChain.end,
                        top = likeInfoIcon.top,
                        bottom = likeInfoIcon.bottom,
                        end = parent.end
                    )
                },
            text = data?.trackLikeCountInfo.orEmpty(),
            style = YouTopAppTheme.typography.titleSmall,
            color = YouTopAppTheme.colors.primaryBackground
        )

        BottomAllSongsContainerScreen(
            modifier = Modifier
                .constrainAs(bottomSongInfoContainer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(likeInfoIcon.bottom, 26.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            showMessage = {
                musicTrackViewModel.showMessage(message = it)
            }
        )
        SnackbarHost(
            modifier = Modifier
                .constrainAs(snackBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            hostState = snackBarHostState.value,
            snackbar = {
                if (isErrorMessageShow) {
                    Snackbar(
                        action = {
                            TextButton(onClick = {
                                musicTrackViewModel.apply {
                                    loadData()
                                    loadAllSongsData()
                                }
                                snackBarHostState.value.currentSnackbarData?.dismiss()
                            }) {
                                Text(
                                    text = "Refresh",
                                    style = YouTopAppTheme.typography.titleMediumBold,
                                    color = YouTopAppTheme.colors.errorColor
                                )
                            }
                        }
                    ) {
                        Text(snackBarHostState.value.currentSnackbarData?.message.orEmpty())
                    }
                } else {
                    Snackbar() {
                        Text(snackBarHostState.value.currentSnackbarData?.message.orEmpty())
                    }
                }
            }
        )

        when (val viewState = musicTrackScreenViewState.viewState) {
            is ViewState.Success -> {
                data = viewState.data
            }
            is ViewState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .constrainAs(progressIndicator) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.value(60.dp)
                            width = Dimension.value(60.dp)
                        },
                    color = YouTopAppTheme.colors.secondaryBackground
                )
            }
            is ViewState.Error -> {
                musicTrackViewModel.showMessage(message = viewState.toString(), isError = true)
            }
            is ViewState.Empty -> {}
        }
    }
}
