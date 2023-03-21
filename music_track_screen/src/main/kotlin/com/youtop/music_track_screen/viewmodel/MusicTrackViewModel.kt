package com.youtop.music_track_screen.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.youtop.core.extensions.asMutable
import com.youtop.core.models.ViewState
import com.youtop.core.models.map
import com.youtop.core.models.mapResultData
import com.youtop.core.models.toViewState
import com.youtop.core.view_models.BaseViewModel
import com.youtop.domain.usecase.GetAllSongsUseCase
import com.youtop.domain.usecase.GetTrackDataUseCase
import com.youtop.music_track_screen.mapper.MusicTrackDomainToSongItemUIMapper
import com.youtop.music_track_screen.mapper.MusicTrackDomainToUIMapper
import com.youtop.music_track_screen.models.MusicTrackScreenSideEffect
import com.youtop.music_track_screen.models.MusicTrackScreenViewState
import com.youtop.music_track_screen.models.MusicTrackUIModel
import com.youtop.music_track_screen.models.SongItemUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Stable
internal class MusicTrackViewModel(
    private val getTrackDataUseCase: GetTrackDataUseCase,
    private val getAllSongsUseCase: GetAllSongsUseCase,
    private val uiMapper: MusicTrackDomainToUIMapper,
    private val songUiMapper: MusicTrackDomainToSongItemUIMapper
) : ContainerHost<MusicTrackScreenViewState, MusicTrackScreenSideEffect>, BaseViewModel() {

    override val container = container<MusicTrackScreenViewState, MusicTrackScreenSideEffect>(
        MusicTrackScreenViewState.initial()
    )

    companion object {
        private const val DEFAULT_TRACK_DATA_LIMIT = 10
        private const val VALID_ALBUM_ID = "62862c2b2eb9cc224736a155"
    }

    private val viewStateMutableStateFlow =
        MutableStateFlow<ViewState<MusicTrackUIModel>>(ViewState.Loading)

    val allSongsViewState: StateFlow<ViewState<List<SongItemUIModel>>> =
        MutableStateFlow<ViewState<List<SongItemUIModel>>>(ViewState.Loading)


    fun loadData(albumId: String) {
        viewModelScope.launch {
            // albumId not valid, requested by VALID_ALBUM_ID
            postViewState(ViewState.Loading)
            val response =
                getTrackDataUseCase.invoke(
                    parameters = if (albumId.isNotBlank()) VALID_ALBUM_ID else VALID_ALBUM_ID
                )
            postViewState(
                response.map {
                    uiMapper.mapFrom(input = it)
                }
                    .toViewState(isEmpty = { false })
            )
        }
    }

    fun loadAllSongsData() {
        viewModelScope.launch {
            val response = getAllSongsUseCase.invoke(DEFAULT_TRACK_DATA_LIMIT)
            postSongsViewState(
                response.mapResultData {
                    it.map { trackResponseData ->
                        songUiMapper.mapFrom(trackResponseData)
                    }
                }
                    .first()
                    .toViewState { it.isEmpty() }
            )
        }
    }

    fun onBackButtonClicked() {
        intent {
            postSideEffect(
                MusicTrackScreenSideEffect.NavigateToBackStack
            )
        }
    }

    fun showMessage(message: String, isError: Boolean = false) {
        intent {
            postSideEffect(
                MusicTrackScreenSideEffect.ShowMessage(message = message, isErrorMessage = isError)
            )
        }
    }

    private suspend fun postViewState(newViewState: ViewState<MusicTrackUIModel>) {
        viewStateMutableStateFlow.emit(newViewState)
        reducer()
    }

    private suspend fun postSongsViewState(newViewState: ViewState<List<SongItemUIModel>>) {
        allSongsViewState.asMutable().emit(newViewState)
    }

    private fun reducer() {
        intent {
            reduce {
                MusicTrackScreenViewState(viewStateMutableStateFlow.value)
            }
        }
    }
}