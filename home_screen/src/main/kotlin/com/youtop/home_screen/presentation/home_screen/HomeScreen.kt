package com.youtop.home_screen.presentation.home_screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.youtop.core.R
import com.youtop.core.models.ViewState
import com.youtop.core.navigation.AppRoutes
import com.youtop.core.theme.YouTopAppTheme
import com.youtop.home_screen.models.HomeScreenSideEffect
import com.youtop.home_screen.models.MusicMenuInfoModel
import com.youtop.home_screen.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeScreen(modifier: Modifier, navController: NavController) {

    val homeViewModel: HomeScreenViewModel = koinViewModel()
    val homeScreenViewState by homeViewModel.container.stateFlow.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    val activity = LocalContext.current as Activity
    val statusBarColor: Color = YouTopAppTheme.colors.primaryStatusBarColor
    val systemUiController = rememberSystemUiController()
    val snackBarHostState = remember { mutableStateOf(SnackbarHostState()) }


    LaunchedEffect(key1 = Unit) {
        WindowCompat.setDecorFitsSystemWindows(activity.window, true)
        systemUiController.setStatusBarColor(color = statusBarColor)
        homeViewModel.loadData()
    }

    homeViewModel.collectSideEffect { sideEffects ->
        when (sideEffects) {
            is HomeScreenSideEffect.NavigateToAlbumDetailsScreen -> {
                if (sideEffects.isItemClicked) {
                    navController.navigate(
                        "${AppRoutes.YouTopTrackScreen.route}/${sideEffects.data.albumId}"
                    )
                }
            }
            is HomeScreenSideEffect.SeeAllBtnClicked -> {
                navController.navigate(
                    "${AppRoutes.YouTopTrackScreen.route}/${"see all"}"
                )
            }
            is HomeScreenSideEffect.ShowMessage -> {
                snackBarHostState.value.showSnackbar(
                    message = sideEffects.message,
                    duration = SnackbarDuration.Long
                )
            }
        }
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
        when (val viewState = homeScreenViewState.viewState) {
            is ViewState.Success -> {
                MusicAlbumInfoContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(195.dp)
                        .background(YouTopAppTheme.colors.primaryBackground),
                    albumInfoData = viewState.data,
                    seeAllBtnClicked = {
                        homeViewModel.seeAllBtnClicked()
                    }
                ) {
                    homeViewModel.itemAlbumClicked(data = it)
                }
            }
            is ViewState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(195.dp)
                        .background(YouTopAppTheme.colors.primaryBackground),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredSize(60.dp),
                        color = YouTopAppTheme.colors.secondaryBackground
                    )
                }
            }
            is ViewState.Error -> {
                homeViewModel.showMessage(message = viewState.toString())

            }
            is ViewState.Empty -> {
                homeViewModel.showMessage(message = viewState.toString())
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        SnackbarHost(
            modifier = Modifier
                .fillMaxWidth(1f),
            hostState = snackBarHostState.value,
            snackbar = {

                Snackbar(
                    action = {
                        TextButton(onClick = {
                            homeViewModel.loadData()
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
            }
        )
        MusicMenuInfoScreen(
            modifier = Modifier.fillMaxSize(),
            data = initialListData()
        ) {

        }
    }
}

private fun initialListData() = listOf(
    MusicMenuInfoModel(
        id = 0,
        resIcon = R.drawable.ic_playlist,
        resTitle = R.string.music_menu_play_list_title
    ),
    MusicMenuInfoModel(
        id = 1,
        resIcon = R.drawable.ic_track,
        resTitle = R.string.music_menu_track_title
    ),
    MusicMenuInfoModel(
        id = 2,
        resIcon = R.drawable.ic_album,
        resTitle = R.string.music_menu_albums_title
    ),
    MusicMenuInfoModel(
        id = 3,
        resIcon = R.drawable.ic_microphone,
        resTitle = R.string.music_menu_artist_title
    ),
    MusicMenuInfoModel(
        id = 4,
        resIcon = R.drawable.ic_headphones,
        resTitle = R.string.music_menu_listened_title
    ),
    MusicMenuInfoModel(
        id = 5,
        resIcon = R.drawable.ic_downloades,
        resTitle = R.string.music_menu_downloads_title
    ),
)
