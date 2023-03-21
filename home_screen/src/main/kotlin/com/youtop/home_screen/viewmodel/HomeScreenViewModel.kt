package com.youtop.home_screen.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.youtop.core.models.ViewState
import com.youtop.core.models.mapResultData
import com.youtop.core.models.toViewState
import com.youtop.core.view_models.BaseViewModel
import com.youtop.domain.models.GetAlbumRequestModel
import com.youtop.domain.usecase.GetAlbumUseCase
import com.youtop.home_screen.mapper.AlbumInfoDomainToUIMapper
import com.youtop.home_screen.models.AlbumInfoItemUIModel
import com.youtop.home_screen.models.HomeScreenSideEffect
import com.youtop.home_screen.models.HomeScreenViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Stable
internal class HomeScreenViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
    private val uiMapper: AlbumInfoDomainToUIMapper
) : ContainerHost<HomeScreenViewState, HomeScreenSideEffect>, BaseViewModel() {

    companion object {
        private const val DEFAULT_LIMIT_DATA = 5
        private const val DEFAULT_OFFSET = 0
    }

    override val container = container<HomeScreenViewState, HomeScreenSideEffect>(
        HomeScreenViewState.initial()
    )

    @VisibleForTesting
    val viewStateMutableStateFlow =
        MutableStateFlow<ViewState<List<AlbumInfoItemUIModel>>>(ViewState.Loading)

    fun loadData(limit: Int = DEFAULT_LIMIT_DATA, offset: Int = DEFAULT_OFFSET) {
        viewModelScope.launch {
            postViewState(ViewState.Loading)
            val response = getAlbumUseCase.invoke(parameters = GetAlbumRequestModel(limit, offset))
            postViewState(response.mapResultData {
                it.map { albumResponseDomain ->
                    uiMapper.mapFrom(input = albumResponseDomain)
                }
            }
                .first()
                .toViewState { it.isEmpty() })
        }
    }

    fun itemAlbumClicked(data: AlbumInfoItemUIModel) {
        intent {
            postSideEffect(
                HomeScreenSideEffect.NavigateToAlbumDetailsScreen(
                    isItemClicked = true,
                    data = data
                )
            )
        }
    }

    fun seeAllBtnClicked() {
        intent {
            postSideEffect(
                HomeScreenSideEffect.SeeAllBtnClicked
            )
        }
    }


    fun showMessage(message: String) {
        intent {
            postSideEffect(
                HomeScreenSideEffect.ShowMessage(
                    message = message
                )
            )
        }
    }

    private suspend fun postViewState(newViewState: ViewState<List<AlbumInfoItemUIModel>>) {
        viewStateMutableStateFlow.emit(newViewState)
        reducer()
    }

    private fun reducer() {
        intent {
            reduce {
                HomeScreenViewState(viewStateMutableStateFlow.value)
            }
        }
    }
}