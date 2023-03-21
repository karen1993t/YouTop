package com.youtop.home_screen

import com.youtop.core.models.Result
import com.youtop.core.models.ViewState
import com.youtop.domain.models.AlbumsInfoDomainModelItem
import com.youtop.domain.models.CoverImageDomainModel
import com.youtop.domain.models.DimensionsDomainModel
import com.youtop.domain.models.GetAlbumRequestModel
import com.youtop.domain.usecase.GetAlbumUseCase
import com.youtop.home_screen.mapper.AlbumInfoDomainToUIMapper
import com.youtop.home_screen.models.AlbumInfoItemUIModel
import com.youtop.home_screen.viewmodel.HomeScreenViewModel
import com.youtop.test_utils.MainDispatcherRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner.Silent::class)
internal class HomeScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var uiMapper: AlbumInfoDomainToUIMapper

    @Mock
    lateinit var getAlbumUseCase: GetAlbumUseCase

    private lateinit var homeScreenViewModel: HomeScreenViewModel

    @Before
    fun beforeTest() {
        homeScreenViewModel = HomeScreenViewModel(
            getAlbumUseCase = getAlbumUseCase,
            uiMapper = uiMapper
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load album info data with Success state`() = runTest {
        // given
        `when`(getAlbumUseCase.invoke(GetAlbumRequestModel(limit = 5, offset = 0))).thenReturn(
            flow {
                emit(
                    Result.Success(
                        data = listOf(
                            AlbumsInfoDomainModelItem(
                                artists = emptyList(),
                                contentWarning = false,
                                coverImage = CoverImageDomainModel(
                                    dimensions = DimensionsDomainModel(height = 0, width = 0),
                                    extension = "",
                                    id = "",
                                    name = "",
                                    path = ""
                                ),
                                id = "",
                                isFavorite = false,
                                likeCount = 0,
                                slug = "",
                                subTitle = "",
                                title = "",
                                tracksCount = 0,
                                type = ""
                            )
                        )
                    )
                )
            }.flowOn(UnconfinedTestDispatcher())
        )
        val homeScreenViewState = mutableListOf<ViewState<List<AlbumInfoItemUIModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            homeScreenViewModel.viewStateMutableStateFlow.toList(homeScreenViewState)
        }
        // when
        homeScreenViewModel.loadData()

        // then
        verify(getAlbumUseCase, times(1)).invoke(GetAlbumRequestModel(5, 0))
        TestCase.assertTrue(homeScreenViewState.firstOrNull()?.isLoading() == true)
        TestCase.assertTrue(homeScreenViewState.lastOrNull()?.isSuccess() == true)
        collectViewState.cancel()

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load album info data with Success state and empty data`() = runTest {
        // given
        `when`(getAlbumUseCase.invoke(GetAlbumRequestModel(limit = 5, offset = 0))).thenReturn(
            flow<Result<List<AlbumsInfoDomainModelItem>>> {
                emit(
                    Result.Success(
                        data = emptyList()
                    )
                )
            }.flowOn(UnconfinedTestDispatcher())
        )

        val homeScreenViewState = mutableListOf<ViewState<List<AlbumInfoItemUIModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            homeScreenViewModel.viewStateMutableStateFlow.toList(homeScreenViewState)
        }
        // when
        homeScreenViewModel.loadData()

        // then
        verify(getAlbumUseCase, times(1)).invoke(GetAlbumRequestModel(5, 0))
        TestCase.assertTrue(homeScreenViewState.firstOrNull()?.isLoading() == true)
        TestCase.assertTrue(homeScreenViewState.lastOrNull()?.isEmpty() == true)
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load album info data with Error state`() = runTest {
        // given
        val networkErrorMessage = "network not connection"
        `when`(getAlbumUseCase.invoke(GetAlbumRequestModel(limit = 5, offset = 0))).thenReturn(
            flow<Result<List<AlbumsInfoDomainModelItem>>> {
                emit(
                    Result.Error(
                        Throwable(message = networkErrorMessage)
                    )
                )
            }.flowOn(UnconfinedTestDispatcher())
        )

        val homeScreenViewState = mutableListOf<ViewState<List<AlbumInfoItemUIModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            homeScreenViewModel.viewStateMutableStateFlow.toList(homeScreenViewState)
        }
        // when
        homeScreenViewModel.loadData()

        // then
        verify(getAlbumUseCase, times(1)).invoke(GetAlbumRequestModel(5, 0))
        TestCase.assertTrue(homeScreenViewState.firstOrNull()?.isLoading() == true)
        TestCase.assertTrue(homeScreenViewState.lastOrNull()?.isError() == true)
        TestCase.assertEquals(
            (homeScreenViewState.lastOrNull() as? ViewState.Error)?.errorMessage,
            networkErrorMessage
        )
        collectViewState.cancel()
    }
}