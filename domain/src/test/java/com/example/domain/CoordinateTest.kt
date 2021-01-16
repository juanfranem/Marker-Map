package com.example.domain

import com.example.domain.coordinate.Coordinate
import com.example.domain.coordinate.exception.LatitudeIsNotValid
import com.example.domain.coordinate.exception.LongitudeIsNotValid
import com.example.domain.coordinate.valueObject.Latitude
import com.example.domain.coordinate.valueObject.Longitude
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class CoordinateTest {

    @Test
    fun checkLatitudeIsNotValidAndLongitudeIsValid() {
        try {
            createCoordinate(-91.0, 40.0)
            Assert.fail()
        } catch (e: Exception) {
            assert(e is LatitudeIsNotValid)
        }
    }

    @Test
    fun checkLatitudeIsValidAndLongitudeIsNotValid() {
        try {
            createCoordinate(-89.0, 181.0)
            Assert.fail()
        } catch (e: Exception) {
            assert(e is LongitudeIsNotValid)
        }
    }

    @Test
    fun checkAllIsValid() {
        try {
            createCoordinate(-89.0, 177.0)
            assert(true)
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    private fun createCoordinate(latitude: Double, longitude: Double) {
        Coordinate(Latitude(latitude), Longitude(longitude))
    }
}