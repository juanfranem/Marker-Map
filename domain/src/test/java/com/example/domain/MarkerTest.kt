package com.example.domain

import com.example.domain.coordinate.Coordinate
import com.example.domain.coordinate.valueObject.Latitude
import com.example.domain.coordinate.valueObject.Longitude
import com.example.domain.marker.Marker
import com.example.domain.marker.exception.CompanyMustBeMoreThanZero
import com.example.domain.marker.exception.IdCantBeEmpty
import com.example.domain.marker.exception.NameCantBeEmpty
import com.example.domain.marker.valueObject.Company
import com.example.domain.marker.valueObject.Id
import com.example.domain.marker.valueObject.Name
import org.junit.Assert
import org.junit.Test

class MarkerTest {

    @Test
    fun checkIdCantBeEmpty() {
        try {
            createMarker("", "NOTEMPTYSTRING", 2)
            Assert.fail()
        } catch (e: Exception) {
            assert(e is IdCantBeEmpty)
        }
    }

    @Test
    fun checkNameCantBeEmptyOrNull() {
        try {
            createMarker("NOTEMPTYSTRING", "", 2)
            Assert.fail()
        } catch (e: Exception) {
            assert(e is NameCantBeEmpty)
        }
    }

    @Test
    fun checkCompanyCantBeLessThanZero() {
        try {
            createMarker("NOTEMPTYSTRING", "NOTEMPTYSTRING", 0)
            Assert.fail()
        } catch (e: Exception) {
            assert(e is CompanyMustBeMoreThanZero)
        }
    }

    private fun createMarker(id: String, name: String, company: Int) {
        Marker(Id(id), Company(company), Name(name), Coordinate(Latitude(80.0), Longitude(110.0)))
    }
}