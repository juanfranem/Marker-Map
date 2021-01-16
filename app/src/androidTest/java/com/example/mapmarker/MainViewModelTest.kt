package com.example.mapmarker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.data.marker.useCase.FindMarkersByCityAndCoordinateRange
import com.example.domain.coordinate.Coordinate
import com.example.domain.coordinate.valueObject.Latitude
import com.example.domain.coordinate.valueObject.Longitude
import com.example.mapmarker.ui.main.MainViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        val params = FindMarkersByCityAndCoordinateRange.Params("lisboa", Coordinate(
            Latitude(38.711046),
            Longitude(-9.160096)
        ), Coordinate(Latitude(38.739429), Longitude(-9.137115)))
        val findMarkersByCityAndCoordinateRange: FindMarkersByCityAndCoordinateRange = mockk()
        coEvery { findMarkersByCityAndCoordinateRange.execute(params) } returns listOf()
        viewModel = MainViewModel(findMarkersByCityAndCoordinateRange)
    }

    @Test
    fun checkLiveData() {
        viewModel.data.observeForever {
            assert(it.isEmpty())
        }
        viewModel.loadMarker("lisboa", Coordinate(
            Latitude(38.711046),
            Longitude(-9.160096)
        ), Coordinate(Latitude(38.739429), Longitude(-9.137115)))
    }


}