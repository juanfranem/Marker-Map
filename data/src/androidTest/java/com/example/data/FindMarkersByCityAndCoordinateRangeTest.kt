package com.example.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.marker.useCase.FindMarkersByCityAndCoordinateRange
import com.example.domain.coordinate.Coordinate
import com.example.domain.coordinate.valueObject.Latitude
import com.example.domain.coordinate.valueObject.Longitude
import com.example.domain.marker.MarkerRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FindMarkersByCityAndCoordinateRangeTest {

    lateinit var findMarkersByCityAndCoordinateRange: FindMarkersByCityAndCoordinateRange

    @Before
    fun setup() {
        val markerRepository: MarkerRepository = mockk(relaxed = true)
        coEvery { markerRepository.findMarkersInRange(Coordinate(Latitude(38.711046), Longitude(-9.160096)), Coordinate(
            Latitude(38.739429), Longitude(-9.137115)), "lisboa") } returns listOf()
        findMarkersByCityAndCoordinateRange = FindMarkersByCityAndCoordinateRange(markerRepository)
    }

    @Test
    fun checkFindMarkersByCityAndCoordinate() {
        val params = FindMarkersByCityAndCoordinateRange.Params("lisboa", Coordinate(Latitude(38.711046),
            Longitude(-9.160096)), Coordinate(Latitude(38.739429), Longitude(-9.137115)))
        GlobalScope.launch {
            findMarkersByCityAndCoordinateRange(params, onError = {
                Assert.fail()
            }, onResult = { response ->
                assert(response.isEmpty())
            })

        }

    }

}