package com.youtop.music_track_screen

import com.youtop.core.models.Result
import com.youtop.core.models.ViewState
import com.youtop.domain.models.SongDomainModel
import com.youtop.domain.models.TrackCoverImageDomainModel
import com.youtop.domain.models.TrackInfoItemDomainModel
import com.youtop.domain.usecase.GetAllSongsUseCase
import com.youtop.domain.usecase.GetTrackDataUseCase
import com.youtop.music_track_screen.mapper.MusicTrackDomainToSongItemUIMapper
import com.youtop.music_track_screen.mapper.MusicTrackDomainToUIMapper
import com.youtop.music_track_screen.models.MusicTrackUIModel
import com.youtop.music_track_screen.models.SongItemUIModel
import com.youtop.music_track_screen.viewmodel.MusicTrackViewModel
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
internal class MusicTrackViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var trackDomainToSongItemUIMapper: MusicTrackDomainToSongItemUIMapper

    @Mock
    lateinit var musicTrackDomainToUIMapper: MusicTrackDomainToUIMapper

    @Mock
    lateinit var getTrackDataUseCase: GetTrackDataUseCase

    @Mock
    lateinit var getAllSongsUseCase: GetAllSongsUseCase

    private lateinit var musicTrackViewModel: MusicTrackViewModel

    @Before
    fun beforeTest() {
        musicTrackViewModel = MusicTrackViewModel(
            getAllSongsUseCase = getAllSongsUseCase,
            getTrackDataUseCase = getTrackDataUseCase,
            uiMapper = musicTrackDomainToUIMapper,
            songUiMapper = trackDomainToSongItemUIMapper
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load track info data by album id with Success state`() = runTest {
        // given
        val albumId = MusicTrackViewModel.VALID_ALBUM_ID
        `when`(getTrackDataUseCase.invoke(parameters = albumId)).thenReturn(
            Result.Success(
                data = TrackInfoItemDomainModel(
                    albums = emptyList(),
                    artists = emptyList(),
                    contentWarning = false,
                    coverImage = TrackCoverImageDomainModel(
                        dimensions = TrackCoverImageDomainModel.Dimensions(
                            height = 800,
                            width = 800,
                        ),
                        extension = "",
                        id = "",
                        name = "",
                        path = ""
                    ),
                    createdAt = "2023.21.03",
                    description = "",
                    downloaded = false,
                    id = "",
                    isFavorite = false,
                    isHidden = false,
                    isTop = false,
                    likeCount = 0,
                    slug = "",
                    song = SongDomainModel(
                        duration = 123.0,
                        extension = "",
                        id = "",
                        name = "",
                        path = ""
                    ),
                    subTitle = "",
                    title = "",
                    type = ""
                )
            )
        )
        val musicTrackScreenViewState = mutableListOf<ViewState<MusicTrackUIModel>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            musicTrackViewModel.viewStateMutableStateFlow.toList(musicTrackScreenViewState)
        }
        // when
        musicTrackViewModel.loadData(albumId)

        // then
        verify(getTrackDataUseCase, times(1)).invoke(parameters = albumId)
        TestCase.assertTrue(musicTrackScreenViewState.firstOrNull()?.isLoading() == true)
        TestCase.assertTrue(musicTrackScreenViewState.lastOrNull()?.isSuccess() == true)
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load track info data by album id with Error state`() = runTest {
        // given
        val albumId = MusicTrackViewModel.VALID_ALBUM_ID
        val errorMessage = "Network Error"
        `when`(getTrackDataUseCase.invoke(parameters = albumId)).thenReturn(
            Result.Error(Throwable(message = errorMessage))
        )
        val musicTrackScreenViewState = mutableListOf<ViewState<MusicTrackUIModel>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            musicTrackViewModel.viewStateMutableStateFlow.toList(musicTrackScreenViewState)
        }
        // when
        musicTrackViewModel.loadData(albumId)

        // then
        verify(getTrackDataUseCase, times(1)).invoke(parameters = albumId)
        TestCase.assertTrue(musicTrackScreenViewState.firstOrNull()?.isLoading() == true)
        TestCase.assertTrue(musicTrackScreenViewState.lastOrNull()?.isError() == true)
        TestCase.assertEquals(
            (musicTrackScreenViewState.lastOrNull() as? ViewState.Error)?.errorMessage,
            errorMessage
        )
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load all songs info data with Success state`() = runTest {
        // given
        `when`(getAllSongsUseCase.invoke(parameters = MusicTrackViewModel.DEFAULT_TRACK_DATA_LIMIT)).thenReturn(
            flow<Result<List<TrackInfoItemDomainModel>>> {
                emit(
                    Result.Success(
                        data = listOf(
                            TrackInfoItemDomainModel(
                                albums = emptyList(),
                                artists = emptyList(),
                                contentWarning = false,
                                coverImage = TrackCoverImageDomainModel(
                                    dimensions = TrackCoverImageDomainModel.Dimensions(
                                        height = 800,
                                        width = 800,
                                    ),
                                    extension = "",
                                    id = "",
                                    name = "",
                                    path = ""
                                ),
                                createdAt = "2023.21.03",
                                description = "",
                                downloaded = false,
                                id = "",
                                isFavorite = false,
                                isHidden = false,
                                isTop = false,
                                likeCount = 0,
                                slug = "",
                                song = SongDomainModel(
                                    duration = 123.0,
                                    extension = "",
                                    id = "",
                                    name = "",
                                    path = ""
                                ),
                                subTitle = "",
                                title = "",
                                type = ""
                            )
                        )
                    )
                )
            }.flowOn(UnconfinedTestDispatcher())
        )
        val songsScreenViewState = mutableListOf<ViewState<List<SongItemUIModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            musicTrackViewModel.allSongsViewState.toList(songsScreenViewState)
        }
        // when
        musicTrackViewModel.loadAllSongsData()

        // then
        verify(
            getAllSongsUseCase,
            times(1)
        ).invoke(parameters = MusicTrackViewModel.DEFAULT_TRACK_DATA_LIMIT)
        TestCase.assertTrue(songsScreenViewState.firstOrNull()?.isLoading() == true)
        TestCase.assertTrue(songsScreenViewState.lastOrNull()?.isSuccess() == true)
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load all songs info data with Success state and also get empty list`() = runTest {
        // given
        `when`(getAllSongsUseCase.invoke(parameters = MusicTrackViewModel.DEFAULT_TRACK_DATA_LIMIT)).thenReturn(
            flow<Result<List<TrackInfoItemDomainModel>>> {
                emit(
                    Result.Success(
                        data = emptyList()
                    )
                )
            }.flowOn(UnconfinedTestDispatcher())
        )
        val songsScreenViewState = mutableListOf<ViewState<List<SongItemUIModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            musicTrackViewModel.allSongsViewState.toList(songsScreenViewState)
        }
        // when
        musicTrackViewModel.loadAllSongsData()

        // then
        verify(
            getAllSongsUseCase,
            times(1)
        ).invoke(parameters = MusicTrackViewModel.DEFAULT_TRACK_DATA_LIMIT)
        TestCase.assertTrue(songsScreenViewState.firstOrNull()?.isLoading() == true)
        TestCase.assertTrue(songsScreenViewState.lastOrNull()?.isEmpty() == true)
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load all songs info data with Error state`() = runTest {
        // given
        val errorMessage = "Network Error"
        `when`(getAllSongsUseCase.invoke(parameters = MusicTrackViewModel.DEFAULT_TRACK_DATA_LIMIT)).thenReturn(
            flow<Result<List<TrackInfoItemDomainModel>>> {
                emit(
                    Result.Error(
                        Throwable(message = errorMessage)
                    )
                )
            }.flowOn(UnconfinedTestDispatcher())
        )
        val songsScreenViewState = mutableListOf<ViewState<List<SongItemUIModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            musicTrackViewModel.allSongsViewState.toList(songsScreenViewState)
        }
        // when
        musicTrackViewModel.loadAllSongsData()

        // then
        verify(
            getAllSongsUseCase,
            times(1)
        ).invoke(parameters = MusicTrackViewModel.DEFAULT_TRACK_DATA_LIMIT)
        TestCase.assertTrue(songsScreenViewState.firstOrNull()?.isLoading() == true)
        TestCase.assertTrue(songsScreenViewState.lastOrNull()?.isError() == true)
        TestCase.assertEquals(
            (songsScreenViewState.lastOrNull() as? ViewState.Error)?.errorMessage,
            errorMessage
        )
        collectViewState.cancel()
    }
}